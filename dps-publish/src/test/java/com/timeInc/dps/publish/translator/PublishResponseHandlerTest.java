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
package com.timeInc.dps.publish.translator;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.timeInc.dps.publish.response.Result;
import com.timeInc.dps.translator.ResponseHandlerException;
import com.timeInc.dps.translator.ResponseTranslator;


@RunWith(JMock.class)
public class PublishResponseHandlerTest {

	private final Mockery context = new Mockery();
	private final ResponseTranslator translator = context.mock(ResponseTranslator.class);
	
	private final PublishResponseHandler responseHandler = new PublishResponseHandler(translator);
	
	
	private static final Result SUCCESSFUL = new Result();
	private static final Result FAILURE = new Result("anything besides Result.Success","message");
	
	@Test
	public void usesTranslatorToTranslateMessage() throws Exception {
		context.checking(new Expectations() {{ 		
			exactly(1).of(translator).convertToObject(with(Result.class),with(any(String.class))); 
			will(returnValue(SUCCESSFUL));
		}}); 
		
		responseHandler.handleResponse(Result.class,"dont_care");
	}
	
	
	@Test (expected = ResponseHandlerException.class)  
	public void throwsAnExceptionIfStatusIsNotOk() throws Exception {
		context.checking(new Expectations() {{ 		
			exactly(1).of(translator).convertToObject(with(Result.class),with(any(String.class))); 
			will(returnValue(FAILURE));
		}}); 
			
		responseHandler.handleResponse(Result.class,"dont_care");
	}
	
	
	
		
}
