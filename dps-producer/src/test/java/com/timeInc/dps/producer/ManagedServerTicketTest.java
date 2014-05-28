/*******************************************************************************
 * Copyright 2014 Time Inc
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.timeInc.dps.producer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.hamcrest.Matcher;
import org.hamcrest.core.IsInstanceOf;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.integration.junit4.JMock;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.timeInc.dps.producer.ManagedServerTicket;
import com.timeInc.dps.producer.HttpProducer;
import com.timeInc.dps.producer.exception.ManagedProducerException;
import com.timeInc.dps.producer.http.request.DownloadRequest;
import com.timeInc.dps.producer.http.request.ModifiableTicketRequest;
import com.timeInc.dps.producer.http.request.TicketRequest;
import com.timeInc.dps.producer.http.request.binding.NewServerRequest;
import com.timeInc.dps.producer.http.request.binding.OpenSessionRequest;
import com.timeInc.dps.producer.request.config.CloseSessionConfig;
import com.timeInc.dps.producer.request.config.OpenSessionConfig;
import com.timeInc.dps.producer.response.OpenSession;
import com.timeInc.dps.producer.response.Server;
import com.timeInc.dps.producer.response.Status;


@RunWith(JMock.class)
public class ManagedServerTicketTest {
	private final Mockery context = new Mockery();

	private final HttpProducer mockProducer = context.mock(HttpProducer.class);
	private final ModifiableTicketRequest mockTicketRequest = context.mock(ModifiableTicketRequest.class);
	final Sequence ticketRequestSequence = context.sequence("sequence-name");

	private final String apiAddress = "http://www.testapi.com";

	private final ManagedServerTicket client = new ManagedServerTicket(mockProducer,apiAddress);

	private final CloseSessionConfig ANY_CLOSE_SESSION = null;

	private final DownloadRequest mockDownloadRequest =  context.mock(DownloadRequest.class);

	private final NewServerRequest NEW_SERVER_REQUEST = new NewServerRequest(apiAddress);
	private final Server NEW_SERVER_RESPONSE = new Server("status","http://newserver.com","http://newdownloadserver.com");

	private final OpenSessionConfig ANY_OPEN_SESSION = null;
	private final OpenSessionRequest OPEN_SESSION_REQUEST = new OpenSessionRequest(apiAddress,ANY_OPEN_SESSION);
	private final OpenSession OPEN_SESSION_RESPONSE = new OpenSession("authToken","status","ticket","downloadTicket","http://server.com","https://downloadServer.com",false,20);

	public final Status STATUS_INFO_RESPONSE = new Status("status","statusticket","statusdownloadticket");


	@Test
	public void invokesProducerWithOpenSessionRequestWhenOpening() {
		context.checking(new Expectations() {{ 		
			exactly(1).of(mockProducer).sendRequest(with(isA(OpenSessionRequest.class)));
			will(returnValue(OPEN_SESSION_RESPONSE)); 
		}}); 			

		client.open(ANY_OPEN_SESSION);
	}

	@Test
	public void usesApiAddressWhenOpening() {
		context.checking(new Expectations() {{ 		
			exactly(1).of(mockProducer).sendRequest(OPEN_SESSION_REQUEST); // TODO: convert to matcher
			will(returnValue(OPEN_SESSION_RESPONSE)); 
		}}); 

		client.open(ANY_OPEN_SESSION);
	}

	@Test
	public void invokesProducerToGetATicketRequest() {
		context.checking(new Expectations() {{ 		
			allowing(mockProducer).sendRequest(with(isA(OpenSessionRequest.class)));
			will(returnValue(OPEN_SESSION_RESPONSE)); 

			ignoring(mockTicketRequest);

			exactly(1).of(mockProducer).sendRequest(with(any(TicketRequest.class)));		
			will(returnValue(STATUS_INFO_RESPONSE));
		}}); 		

		client.open(ANY_OPEN_SESSION);
		client.sendRequest(mockTicketRequest);
	}


	@Test
	public void usesTicketFromOpenSessionWhenInitialRequestIsMade() {
		context.checking(new Expectations() {{ 		
			allowing(mockProducer).sendRequest(with(isA(OpenSessionRequest.class)));
			will(returnValue(OPEN_SESSION_RESPONSE)); 

			allowing(mockProducer).sendRequest(with(any(TicketRequest.class)));
			will(returnValue(STATUS_INFO_RESPONSE));

			ignoring(mockTicketRequest).setBasePath(with(any(String.class)));

			exactly(1).of(mockTicketRequest).setTicket(OPEN_SESSION_RESPONSE.getTicket());			
		}}); 		

		client.open(ANY_OPEN_SESSION);
		client.sendRequest(mockTicketRequest);
	}


	@Test
	public void usesNewTicketIfPreviousTicketRequestContainedATicket() {
		context.checking(new Expectations() {{ 		
			allowing(mockProducer).sendRequest(with(isA(OpenSessionRequest.class)));
			will(returnValue(OPEN_SESSION_RESPONSE)); 

			allowing(mockProducer).sendRequest(with(any(TicketRequest.class)));
			will(returnValue(STATUS_INFO_RESPONSE));

			allowing(mockTicketRequest).setTicket(OPEN_SESSION_RESPONSE.getTicket());		

			ignoring(mockTicketRequest).setBasePath(with(any(String.class)));

			exactly(1).of(mockTicketRequest).setTicket(STATUS_INFO_RESPONSE.getTicket());	
		}}); 				

		client.open(ANY_OPEN_SESSION);

		client.sendRequest(mockTicketRequest);

		client.sendRequest(mockTicketRequest);
	}


	@Test
	public void invokesProducerForDownloadRequest() {
		context.checking(new Expectations() {{ 		
			allowing(mockProducer).sendRequest(with(isA(OpenSessionRequest.class)));
			will(returnValue(OPEN_SESSION_RESPONSE)); 

			ignoring(mockDownloadRequest);

			exactly(1).of(mockProducer).retrieve(mockDownloadRequest);
		}}); 			

		client.open(ANY_OPEN_SESSION);	
		client.retrieve(mockDownloadRequest);
	}


	@Test
	public void usesDownloadTicketFromOpenSessionWhenInitialRequestIsMade() {
		context.checking(new Expectations() {{ 		
			allowing(mockProducer).sendRequest(with(isA(OpenSessionRequest.class)));
			will(returnValue(OPEN_SESSION_RESPONSE)); 

			allowing(mockProducer).retrieve(with(isA(DownloadRequest.class)));

			ignoring(mockTicketRequest).setBasePath(with(any(String.class)));

			exactly(1).of(mockTicketRequest).setTicket(OPEN_SESSION_RESPONSE.getDownloadTicket());			
		}}); 	

		client.open(ANY_OPEN_SESSION);	
		client.retrieve(getMockedWrappedRequest(mockTicketRequest));		
	}	


	@Test
	@Ignore
	public void usesNewDownloadTicketIfPreviousTicketRequestContainedADownloadTicket() {
		context.checking(new Expectations() {{ 		
			allowing(mockProducer).sendRequest(with(isA(OpenSessionRequest.class)));
			will(returnValue(OPEN_SESSION_RESPONSE)); 

			allowing(mockProducer).sendRequest(with(any(TicketRequest.class)));
			will(returnValue(STATUS_INFO_RESPONSE));

			allowing(mockProducer).retrieve(with(isA(DownloadRequest.class)));

			ignoring(mockTicketRequest).setBasePath(with(any(String.class)));

			exactly(1).of(mockTicketRequest).setTicket(STATUS_INFO_RESPONSE.getDownloadTicket());	
		}}); 				

		client.open(ANY_OPEN_SESSION);

		client.sendRequest(mockTicketRequest);

		client.retrieve(getMockedWrappedRequest(mockTicketRequest));	
	}


	@Test
	public void usesServerFromOpenSessionForApiRequest() {
		context.checking(new Expectations() {{ 		
			allowing(mockProducer).sendRequest(with(isA(OpenSessionRequest.class)));
			will(returnValue(OPEN_SESSION_RESPONSE)); 

			allowing(mockProducer).sendRequest(with(any(TicketRequest.class)));
			will(returnValue(STATUS_INFO_RESPONSE));

			ignoring(mockTicketRequest).setTicket(with(any(String.class)));

			exactly(1).of(mockTicketRequest).setBasePath(OPEN_SESSION_RESPONSE.getServer());			
		}}); 		

		client.open(ANY_OPEN_SESSION);
		client.sendRequest(mockTicketRequest);		
	}


	@Test
	public void usesServerFromOpenSessionForDownloadApiRequest() {
		context.checking(new Expectations() {{ 		
			allowing(mockProducer).sendRequest(with(isA(OpenSessionRequest.class)));
			will(returnValue(OPEN_SESSION_RESPONSE)); 

			allowing(mockProducer).retrieve(with(isA(DownloadRequest.class)));

			ignoring(mockTicketRequest).setTicket(with(any(String.class)));

			exactly(1).of(mockTicketRequest).setBasePath(OPEN_SESSION_RESPONSE.getDownloadServer());			
		}}); 		

		client.open(ANY_OPEN_SESSION);
		client.retrieve(getMockedWrappedRequest(mockTicketRequest));		
	}


	@Test
	public void invokesProducerWhenNewServerIsRequested() {
		context.checking(new Expectations() {{ 		
			allowing(mockProducer).sendRequest(with(isA(OpenSessionRequest.class)));
			will(returnValue(OPEN_SESSION_RESPONSE)); 

			exactly(1).of(mockProducer).sendRequest(with(isA(NewServerRequest.class)));
			will(returnValue(NEW_SERVER_RESPONSE));
		}}); 

		client.open(ANY_OPEN_SESSION);
		client.useNewServer();
	}

	@Test
	public void usesBaseApiAddressWhenNewServerIsRequested() {
		context.checking(new Expectations() {{ 		
			allowing(mockProducer).sendRequest(with(isA(OpenSessionRequest.class)));
			will(returnValue(OPEN_SESSION_RESPONSE)); 

			exactly(1).of(mockProducer).sendRequest(with(any(NewServerRequest.class)));
			will(returnValue(NEW_SERVER_RESPONSE));
		}}); 

		client.open(ANY_OPEN_SESSION);		
		client.useNewServer();
	}


	@Test
	public void usesNewServerForApiRequests() {
		context.checking(new Expectations() {{ 		
			allowing(mockProducer).sendRequest(with(isA(OpenSessionRequest.class)));
			will(returnValue(OPEN_SESSION_RESPONSE)); 

			allowing(mockProducer).sendRequest(with(any(TicketRequest.class)));
			will(returnValue(NEW_SERVER_RESPONSE));

			ignoring(mockTicketRequest).setTicket(with(any(String.class)));
			
			exactly(1).of(mockTicketRequest).setBasePath(NEW_SERVER_RESPONSE.getServer());						
		}}); 

		client.open(ANY_OPEN_SESSION);		
		client.useNewServer();

		client.sendRequest(mockTicketRequest);	
	}

	@Test
	public void isNotOpenWhenCloseIsCalledOnAnOpenSession() {
		context.checking(new Expectations() {{ 		
			allowing(mockProducer).sendRequest(with(isA(OpenSessionRequest.class)));
			will(returnValue(OPEN_SESSION_RESPONSE)); 
			
			allowing(mockProducer).sendRequest(with(any(TicketRequest.class)));

		}}); 

		client.open(ANY_OPEN_SESSION);	
		client.close(ANY_CLOSE_SESSION);
		assertFalse(client.isOpen());
	}

	@Test
	public void notOpenWhenOpenIsNotCalled() {
		assertFalse(client.isOpen());
	}

	@Test
	public void isOpenWhenOpenIsCalled() {
		context.checking(new Expectations() {{ 		
			ignoring(mockProducer);
			will(returnValue(OPEN_SESSION_RESPONSE)); 
		}}); 


		client.open(ANY_OPEN_SESSION);
		assertTrue(client.isOpen());
	}

	@Test(expected = ManagedProducerException.class)
	public void failsWhenSendingModifiableFolioRequestAndSessionNotOpened() {
		client.sendRequest(mockTicketRequest);
	}

	private static <T> Matcher<T> isA(Class<T> type) {
		return (Matcher<T>) new IsInstanceOf(type);
	}		
	
	
	private static DownloadRequest getMockedWrappedRequest(final ModifiableTicketRequest<Status> wrappedMock) {
		return new DownloadRequest() {

			@Override
			public ModifiableTicketRequest<Status> getWrappedRequest() {
				return wrappedMock;
			}
		};
		
	}
	
	
}
