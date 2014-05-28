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
package com.timeInc.dps.http.request;

import com.timeInc.dps.translator.TranslatableRequest;



/**
 * Represents http metadata about a specific Request, such as 
 * the http method, the url, and the response class it maps to.
 *
 * @param <T> the response type
 */
public interface HttpRequestMapper<T> {
	enum Method {
		GET, POST, MULTI_PART, DELETE;
	}
	
	/**
	 * Get the http method for this request
	 * @return the http method
	 */
	Method getMethod();
	

	/**
	 * Get the response class to associate this request with
	 * @return the response class
	 */
	
	Class<T> getResponseClass();
	
	/**
	 * Get the url to make the request to
	 * @return the url
	 */
	String getAbsolutePath();
	
	
	/**
	 * The request to make
	 * @return the request
	 */
	TranslatableRequest getConfig();
}
