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
 * making a Sign In request.
 *
 * See Adobe Publishing API documentation for more information.
 */

public class Credential extends Result {
		
	private final String ticket;
	private final String accountId;
	
	

	/**
	 * Instantiates a new credential.
	 *
	 * @param ticket the ticket
	 * @param accountId the account id
	 */
	public Credential(String ticket, String accountId) {
		super();
		this.ticket = ticket;
		this.accountId = accountId;
	}
	
	
	/**
	 * Gets the ticket.
	 *
	 * @return Ticket to be used in future authenticated calls.
	 */
	public String getTicket() {
		return ticket;
	}

	/**
	 * Gets the account id.
	 *
	 * @return The fulfillment server account id. For example, 8e03ee3b3f8b41f586b2c2f8bf7604e4
	 */
	public String getAccountId() {
		return accountId;
	}
}
