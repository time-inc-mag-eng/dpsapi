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
package com.timeInc.dps.translator;

/**
 * Handles a response by converting it to the appropriate 
 * TranslatableResponse object.
 *
 * @param <T> the response type it can handle
 */
public interface ResponseHandler<T> {
	/**
	 * Deserialize the response string to a the specified
	 * response class.
	 * 
	 * @param responseCls the response of subtype T to convert to
	 * @param response the response string to convert from
	 * @return an object of type V that is populated with the response data
	 * @throws ResponseHandlerException if there was error in converting to the response class or the response 
	 * was a failure
	 */
	<V extends T> V handleResponse(Class<V> responseCls, String response) throws ResponseHandlerException;
}
