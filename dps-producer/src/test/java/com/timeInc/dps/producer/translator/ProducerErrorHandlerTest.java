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
package com.timeInc.dps.producer.translator;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import com.timeInc.dps.producer.response.Status;
import com.timeInc.dps.producer.translator.ProducerResponseHandler;
import com.timeInc.dps.translator.ResponseHandlerException;
import com.timeInc.dps.translator.ResponseTranslator;


@RunWith(JMock.class)
public class ProducerErrorHandlerTest {

	private final Mockery context = new Mockery();
	private final ResponseTranslator translator = context.mock(ResponseTranslator.class);


	private final ProducerResponseHandler processor = new ProducerResponseHandler(translator);
	private static String ANY_MESSAGE = "";

	private static Status ANY_RESPONSE = new Status("ok");
	private static Status ERROR_RESPONSE = new Status("unknown");
	
	
	@Test
	public void usesTranslatorToTranslateMessage() throws Exception {
		context.checking(new Expectations() {{ 		
			exactly(1).of(translator).convertToObject(with(ANY_RESPONSE.getClass()),with(any(String.class))); 
			will(returnValue(ANY_RESPONSE));
		}}); 
		
		processor.handleResponse(Status.class,ANY_MESSAGE);
	}
	
	
	@Test
	public void okStatusMatches() {
		assertThat(Status.SUCCESS,equalToIgnoringCase("ok"));
	}
	
	@Test (expected = ResponseHandlerException.class)  
	public void throwsAnExceptionIfStatusIsNotOk() throws Exception {
		context.checking(new Expectations() {{ 
			allowing(translator).convertToObject(with(ANY_RESPONSE.getClass()),with(any(String.class))); 	
			will(returnValue(ERROR_RESPONSE));
		}}); 
			
		processor.handleResponse(Status.class,ANY_MESSAGE);
	}
}
