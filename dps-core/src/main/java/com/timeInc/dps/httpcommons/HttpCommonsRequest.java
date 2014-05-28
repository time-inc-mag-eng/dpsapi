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

import org.apache.http.client.methods.HttpUriRequest;

import com.timeInc.dps.translator.TranslatableRequest;

/**
 * A bridge between a TranslatableRequest to a 
 * {@code org.apache.http.client.methods.HttpUriRequest}
 */
public interface HttpCommonsRequest {
	
	/**
	 * Gets a {@code org.apache.http.client.methods.HttpUriRequest}
	 * using the specified url
	 * @param path the url to make a request to
	 * @return {@code org.apache.http.client.methods.HttpUriRequest}
	 */
	HttpUriRequest generateRequest(String path);
	
	
	/**
	 * Set the TranslatableRequest to be included
	 * as a parameter of the {@code org.apache.http.client.methods.HttpUriRequest}
	 * 
	 * @param parameter the TranslatableRequest
	 */
	void setParameter(TranslatableRequest parameter);
}
