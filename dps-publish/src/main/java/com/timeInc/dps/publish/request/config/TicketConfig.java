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

/**
 * Request to get the status of a published request
 * See Adobe Publishing API documentation for more information.
 */

public class TicketConfig extends IdentifierConfig {
	private final String ticket;
	private final String requestId;

	/**
	 * Constructs an instance that represents the information for an ongoing publish process.
	 *
	 * @param ticket Authentication ticket returned from sign-in.
	 * @param requestId RequestId as of publish request to be canceled.
	 */
	public TicketConfig(String ticket, String requestId) {
		this.ticket = ticket;
		this.requestId = requestId;
	}
	
	/**
	 * Gets the ticket.
	 *
	 * @return the ticket
	 */
	public String getTicket() {
		return ticket;
	}
	
	/**
	 * Gets the request id.
	 *
	 * @return the request id
	 */
	public String getRequestId() {
		return requestId;
	}
}	
