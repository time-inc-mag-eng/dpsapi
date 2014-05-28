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
package com.timeInc.dps.publish.response;


/**
 * Response that represents the information after
 * making a Publish request.
 *
 * See Adobe Publishing API for more information.
 */

public class Publish extends Result {
	private final String requestId;
	
	/**
	 * Instantiates a new publish.
	 *
	 * @param requestId the request id
	 */
	public Publish(String requestId) {
		this.requestId = requestId;
	}

	
	/**
	 * Gets the request id.
	 *
	 * @return Unique ID for this publish job. Use this ID in other APIs.
	 */
	public String getRequestId() {
		return requestId;
	}
}
