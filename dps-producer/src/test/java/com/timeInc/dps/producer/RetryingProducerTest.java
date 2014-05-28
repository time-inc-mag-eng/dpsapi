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

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.timeInc.dps.producer.ManagedProducer;
import com.timeInc.dps.producer.RetryingManagedProducer;
import com.timeInc.dps.producer.exception.ServiceUnavailableException;
import com.timeInc.dps.producer.http.request.DownloadRequest;
import com.timeInc.dps.producer.http.request.ModifiableTicketRequest;
import com.timeInc.dps.producer.request.config.CloseSessionConfig;
import com.timeInc.dps.producer.request.config.OpenSessionConfig;
import com.timeInc.dps.producer.response.Status;


@RunWith(JMock.class)
public class RetryingProducerTest {
	private final Mockery context = new Mockery();

	private final ManagedProducer mockClient = context.mock(ManagedProducer.class);

	private final ServiceUnavailableException SERVICE_UNAVAILABLE = new ServiceUnavailableException("Server unavail.");

	private final OpenSessionConfig ANY_OPEN_CONFIG = null;
	private final CloseSessionConfig ANY_CLOSE_CONFIG = null;
	private final ModifiableTicketRequest<? extends Status> ANY_MOD_FOLIO_REQUEST = null;
	private final DownloadRequest ANY_DOWNLOAD_REQUEST = null;


	private static final int ZERO_RETRIES = 0;

	private ManagedProducer getRetryClient(int numberOfTimesToRetry) {
		return new RetryingManagedProducer(mockClient,numberOfTimesToRetry);
	}


	@Test
	public void invokesToContainedClientWhenOpening() {
		context.checking(new Expectations() {{ 		
			exactly(1).of(mockClient).open((OpenSessionConfig)with(anything()));
		}}); 	

		getRetryClient(ZERO_RETRIES).open(ANY_OPEN_CONFIG);
	}

	@Test
	public void invokesToContainedClientWhenCheckIsOpen() {
		context.checking(new Expectations() {{ 		
			exactly(1).of(mockClient).isOpen();
		}}); 

		getRetryClient(ZERO_RETRIES).isOpen();
	}


	@Test
	public void invokesToContainedClientWhenClosing() {
		context.checking(new Expectations() {{ 		
			exactly(1).of(mockClient).close((CloseSessionConfig)with(anything()));
		}}); 

		getRetryClient(ZERO_RETRIES).close(ANY_CLOSE_CONFIG);
	}

	@Test
	public void invokesToContainedClientWhenUseNewServer() {
		context.checking(new Expectations() {{ 		
			exactly(1).of(mockClient).useNewServer();
		}}); 

		getRetryClient(ZERO_RETRIES).useNewServer();
	}


	@Test
	public void invokesToContainedClientWhenSendRequest() {
		context.checking(new Expectations() {{ 		
			exactly(1).of(mockClient).sendRequest((ModifiableTicketRequest)with(anything()));
		}}); 

		getRetryClient(ZERO_RETRIES).sendRequest(ANY_MOD_FOLIO_REQUEST);
	}


	@Test
	public void invokesToContainedClientWhenRetrieving() {
		context.checking(new Expectations() {{ 		
			exactly(1).of(mockClient).retrieve((DownloadRequest)with(anything()));
		}}); 

		getRetryClient(ZERO_RETRIES).retrieve(ANY_DOWNLOAD_REQUEST);
	}



	@Test
	public void retriesSendingRequestUpToTimesToRetryIfServiceUnavailableExceptionOccurs() {
		throwUnavailableExceptionExactlyWhenSend(5);

		context.checking(new Expectations() {{ 		
			allowing(mockClient).useNewServer();
			exactly(1).of(mockClient).sendRequest((ModifiableTicketRequest)with(anything()));
		}}); 

		getRetryClient(5).sendRequest(ANY_MOD_FOLIO_REQUEST); // will send request at most 6 times
	}
	
	@Test
	public void retriesDownloadingRequestUpToTimesToRetryIfServiceUnavailableExceptionOccurs() {
		throwUnavailableExceptionExactlyWhenRetrieve(5);
		
		context.checking(new Expectations() {{
			allowing(mockClient).useNewServer();
			exactly(1).of(mockClient).retrieve((DownloadRequest)with(anything()));						
		}}); 
		
		getRetryClient(5).retrieve(ANY_DOWNLOAD_REQUEST); // will send request at most 6 times
	}
	
	@Test
	public void invokesUseNewServerWhenRetryingDownload() {
		throwUnavailableExceptionExactlyWhenRetrieve(5);
		
		context.checking(new Expectations() {{ 	
			exactly(1).of(mockClient).retrieve((DownloadRequest)with(anything()));
			
			exactly(5).of(mockClient).useNewServer();
			
		}}); 
		
		getRetryClient(5).retrieve(ANY_DOWNLOAD_REQUEST); // will send request at most 6 times
	}
	
	
	@Test
	public void invokesUseNewServerWhenRetryingRequest() {
		throwUnavailableExceptionExactlyWhenSend(5);
		
		context.checking(new Expectations() {{ 	
			exactly(1).of(mockClient).sendRequest((ModifiableTicketRequest)with(anything()));
			
			exactly(5).of(mockClient).useNewServer();
			
		}}); 
		
		getRetryClient(5).sendRequest(ANY_MOD_FOLIO_REQUEST); // will send request at most 6 times
	}
	

	private void throwUnavailableExceptionExactlyWhenRetrieve(final int timesToThrowException) {
		context.checking(new Expectations() {{ 		
			exactly(timesToThrowException).of(mockClient).retrieve((DownloadRequest)with(anything()));
			will(throwException(SERVICE_UNAVAILABLE));
		}});
	}
	
	private void throwUnavailableExceptionExactlyWhenSend(final int timesToThrowException) {
		context.checking(new Expectations() {{ 		
			exactly(timesToThrowException).of(mockClient).sendRequest((ModifiableTicketRequest)with(anything()));
			will(throwException(SERVICE_UNAVAILABLE));
		}});
	}
}
