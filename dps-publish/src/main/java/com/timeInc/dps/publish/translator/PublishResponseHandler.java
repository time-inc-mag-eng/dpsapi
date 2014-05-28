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

import com.timeInc.dps.publish.response.Result;
import com.timeInc.dps.translator.ResponseHandler;
import com.timeInc.dps.translator.ResponseHandlerException;
import com.timeInc.dps.translator.ResponseTranslator;

/**
 * Translates the response received from the Publishing API to a subtype of
 * {@link com.timeInc.dps.publish.response.Result}
 */
public class PublishResponseHandler implements ResponseHandler<Result> { 
	private final ResponseTranslator translator; 
	
	/**
	 * Instantiates a new publish response handler.
	 *
	 * @param translator the translator that converts the String response to a subtype of Result
	 */
	public PublishResponseHandler(ResponseTranslator translator) {
		this.translator = translator;
	}

	/** (non-Javadoc)
	 * @see com.timeInc.dps.translator.ResponseHandler#handleResponse(java.lang.Class, java.lang.String)
	 * @throws ResponseHandlerException if the status is not equal to {@link com.timeInc.dps.publish.response.Result#SUCCESS}
	 */
	@Override
	public <V extends Result> V handleResponse(Class<V> responseCls, String response) throws ResponseHandlerException {
		
		V result = translator.convertToObject(responseCls,response);
		
		if(result.getStatus().equals(Result.SUCCESS))
			return result;
		else
			throw new ResponseHandlerException(result.getStatus(), result.getMessage());
	}
}
