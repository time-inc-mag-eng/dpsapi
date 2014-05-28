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
package com.timeInc.dps.publish;

import org.apache.http.client.methods.HttpUriRequest;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.timeInc.dps.httpcommons.HttpSender;
import com.timeInc.dps.httpcommons.RequestFactory;
import com.timeInc.dps.publish.http.request.binding.CancelRequest;
import com.timeInc.dps.publish.http.request.binding.LoginRequest;
import com.timeInc.dps.publish.http.request.binding.PublishRequest;
import com.timeInc.dps.publish.http.request.binding.PublishStatusRequest;
import com.timeInc.dps.publish.http.request.binding.UnpublishRequest;
import com.timeInc.dps.publish.request.config.LoginConfig;
import com.timeInc.dps.publish.request.config.PublishConfig;
import com.timeInc.dps.publish.request.config.TicketConfig;
import com.timeInc.dps.publish.request.config.UnpublishConfig;
import com.timeInc.dps.publish.response.Credential;
import com.timeInc.dps.publish.response.Publish;
import com.timeInc.dps.publish.response.PublishingStatus;
import com.timeInc.dps.publish.response.Result;

@RunWith(JMock.class)
public class HttpPublishClientTest {
	private Mockery context = new JUnit4Mockery() {{
		setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	private final HttpSender<Result> sender = context.mock(HttpSender.class);
	private final RequestFactory reqFactory = context.mock(RequestFactory.class);
	
	
	private Object DONT_CARE_OBJ = null;
	
	private static final String BASE_URL = "http://www.test.com";
	
	private static final String DONT_CARE = "dont_care";
	
	
	private final HttpPublishClient client = new HttpPublishClient(BASE_URL,sender,reqFactory);
	
	
	@Test
	public void publishRequestInvokesRequestFactoryToGenerateRequest() throws Exception {
		context.checking(new Expectations() {{ 		
			allowing(sender).sendRequest(with(any(HttpUriRequest.class)),with(Publish.class));
			will(returnValue(DONT_CARE_OBJ)); 
			
			exactly(1).of(reqFactory).getRequestFor(with(any(PublishRequest.class)));
		}}); 		
		
		client.publish(PublishConfig.getPrivateInstance(DONT_CARE, DONT_CARE, DONT_CARE, false));
	}
	
	@Test
	public void publishRequestInvokesSender() throws Exception {
		context.checking(new Expectations() {{ 		
			exactly(1).of(sender).sendRequest(with(any(HttpUriRequest.class)),with(Publish.class));
			will(returnValue(DONT_CARE_OBJ)); 
			
			allowing(reqFactory).getRequestFor(with(any(PublishRequest.class)));
		}}); 
		
		client.publish(PublishConfig.getPrivateInstance(DONT_CARE, DONT_CARE, DONT_CARE, false));
	}
	
	
	
	@Test
	public void loginRequestInvokesRequestFactoryToGenerateRequest() throws Exception {
		context.checking(new Expectations() {{ 		
			allowing(sender).sendRequest(with(any(HttpUriRequest.class)),with(Credential.class));
			will(returnValue(DONT_CARE_OBJ)); 
			
			exactly(1).of(reqFactory).getRequestFor(with(any(LoginRequest.class)));
		}}); 		
		
		client.signIn(new LoginConfig(DONT_CARE,DONT_CARE));
	}
	
	@Test
	public void loginRequestInvokesSender() throws Exception {
		context.checking(new Expectations() {{ 		
			exactly(1).of(sender).sendRequest(with(any(HttpUriRequest.class)),with(Credential.class));
			will(returnValue(DONT_CARE_OBJ)); 
			
			allowing(reqFactory).getRequestFor(with(any(LoginRequest.class)));
		}}); 		
		
		client.signIn(new LoginConfig(DONT_CARE,DONT_CARE));
	}
	
	
	@Test
	public void publishStatusRequestInvokesRequestFactoryToGenerateRequest() throws Exception {
		context.checking(new Expectations() {{ 		
			allowing(sender).sendRequest(with(any(HttpUriRequest.class)),with(PublishingStatus.class));
			will(returnValue(DONT_CARE_OBJ)); 
			
			exactly(1).of(reqFactory).getRequestFor(with(any(PublishStatusRequest.class)));
		}}); 		
		
		client.getPublishStatus(new TicketConfig(DONT_CARE,DONT_CARE));
	}
	
	
	@Test
	public void publishStatusRequestInvokesSender() throws Exception {
		context.checking(new Expectations() {{ 		
			exactly(1).of(sender).sendRequest(with(any(HttpUriRequest.class)),with(PublishingStatus.class));
			will(returnValue(DONT_CARE_OBJ)); 
			
			allowing(reqFactory).getRequestFor(with(any(PublishStatusRequest.class)));
		}}); 		
		
		client.getPublishStatus(new TicketConfig(DONT_CARE,DONT_CARE));
	}
	
	
	@Test
	public void cancelPublishRequestInvokesRequestFactoryToGenerateRequest() throws Exception {
		context.checking(new Expectations() {{ 		
			allowing(sender).sendRequest(with(any(HttpUriRequest.class)),with(Result.class));
			will(returnValue(DONT_CARE_OBJ)); 
			
			exactly(1).of(reqFactory).getRequestFor(with(any(CancelRequest.class)));
		}}); 		
		
		client.cancelPublish(new TicketConfig(DONT_CARE,DONT_CARE));
	}
	
	
	@Test
	public void cancelPublishRequestInvokesSender() throws Exception {
		context.checking(new Expectations() {{ 		
			exactly(1).of(sender).sendRequest(with(any(HttpUriRequest.class)),with(Result.class));
			will(returnValue(DONT_CARE_OBJ)); 
			
			allowing(reqFactory).getRequestFor(with(any(CancelRequest.class)));
		}}); 		
		
		client.cancelPublish(new TicketConfig(DONT_CARE,DONT_CARE));
	}
	
	@Test
	public void unpublishRequestInvokesRequestFactoryToGenerateRequest() throws Exception {
		context.checking(new Expectations() {{ 		
			allowing(sender).sendRequest(with(any(HttpUriRequest.class)),with(Result.class));
			will(returnValue(DONT_CARE_OBJ)); 
			
			exactly(1).of(reqFactory).getRequestFor(with(any(UnpublishRequest.class)));
		}}); 		
		
		client.unpublishFolio(new UnpublishConfig(DONT_CARE,DONT_CARE));
	}
	
	@Test
	public void unpublishRequestInvokesSender() throws Exception {
		context.checking(new Expectations() {{ 		
			exactly(1).of(sender).sendRequest(with(any(HttpUriRequest.class)),with(Result.class));
			will(returnValue(DONT_CARE_OBJ)); 
			
			allowing(reqFactory).getRequestFor(with(any(UnpublishRequest.class)));
		}}); 		
		
		client.unpublishFolio(new UnpublishConfig(DONT_CARE,DONT_CARE));
	}
	
	
	
}
