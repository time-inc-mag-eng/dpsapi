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
package com.timeInc.dps.publish.request.config;

import com.timeInc.dps.translator.TranslatableRequest;

/**
 * Base configuration that is used by all requests. 
 * 
 * Contains the sessionId and clientVersion
 * 
 * See Adobe Publishing API documentation for more information.
 *
 */

public abstract class IdentifierConfig implements TranslatableRequest {
	private String sessionId;
	private String clientVersion;
	
	/**
	 * Instantiates a new identifier config.
	 */
	public IdentifierConfig() {	}
	
	/**
	 * Constructs an instance using the specified sessionId and clientVersion.
	 *
	 * @param sessionId A unique identifier tied to the caller.
	 * @param clientVersion The version of the client making the call. May include an optional callerIdentifier prefix separated by a dash, such as, �wc-1.2.3�.
	 */
	public IdentifierConfig(String sessionId, String clientVersion) {
		this.sessionId = sessionId;
		this.clientVersion = clientVersion;
	}
	
	
	protected String getSessionId() {
		return sessionId;
	}
	
	
	/**
	 * Sets the session id.
	 *
	 * @param sessionId the new session id
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	/**
	 * Gets the client version.
	 *
	 * @return the client version
	 */
	public String getClientVersion() {
		return clientVersion;
	}
	
	
	/**
	 * Sets the client version.
	 *
	 * @param clientVersion the new client version
	 */
	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}
}
