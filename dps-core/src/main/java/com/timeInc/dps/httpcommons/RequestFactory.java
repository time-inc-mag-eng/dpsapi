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
package com.timeInc.dps.httpcommons;

import java.io.File;

import com.timeInc.dps.http.request.HttpRequestMapper;
import com.timeInc.dps.translator.TranslatableRequest;

/**
 * Converts an {@code com.timeInc.dps.http.request.HttpRequestMapper} to the appropriate
 * HttpCommonsRequest so that it can be sent off to {@code HttpSender}
 */
public class RequestFactory {
	private final AbstractRequestFactory abstractFactory;

	/**
	 * Instantiates a new request factory.
	 *
	 * @param abstractFactory the abstract factory
	 */
	public RequestFactory(AbstractRequestFactory abstractFactory) {
		this.abstractFactory = abstractFactory;
	}
	
	/**
	 * Gets the request using the mapper. If it is a multi-part request that has a file
	 * @see RequestFactory#getRequestFor(HttpRequestMapper, File).
	 *
	 * @param mapper the mapper
	 * @return the request 
	 */
	public HttpCommonsRequest getRequestFor(HttpRequestMapper<?> mapper) {
		return getRequestFor(mapper, null);
	}
	
	/**
	 * Gets the request using the mapper and file. 
	 * File is only useful in the context of a multi-part operation,
	 * otherwise, the file parameter is ignored
	 *
	 * @param mapper the mapper
	 * @param file the file to be included in the multi-part request
	 * @return the request
	 */
	public HttpCommonsRequest getRequestFor(HttpRequestMapper<?> mapper, File file) {
		TranslatableRequest request = mapper.getConfig();
		
		switch(mapper.getMethod()) {
			case GET: 
				return abstractFactory.getMethod(request);
			case POST:
				return abstractFactory.postMethod(request);
			case DELETE:
				return abstractFactory.deleteMethod(request);
			case MULTI_PART: 
				return abstractFactory.multiPartMethod(request, file);
			default: 
				throw new IllegalArgumentException("Can not contruct method of type:" + mapper.getMethod());
		}
	}
	
	
}
