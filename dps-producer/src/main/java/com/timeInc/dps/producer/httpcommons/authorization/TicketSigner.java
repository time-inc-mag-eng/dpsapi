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
package com.timeInc.dps.producer.httpcommons.authorization;

import org.apache.http.HttpRequest;

/**
 * Signs a request by adding the Adobe ticket 
 * to the authorization header.
 */
public class TicketSigner implements HttpSigner {
	
	/** The Constant AUTH_HEADER. */
	public static final String AUTH_HEADER = "Authorization";
	
	/** The Constant ADOBE_TICKET_PARAM. */
	public static final String ADOBE_TICKET_PARAM = "AdobeAuth ticket";
	
	private final String adobeTicket;
	
	/**
	 * Instantiates a new ticket signer.
	 *
	 * @param adobeTicket the adobe ticket
	 */
	public TicketSigner(String adobeTicket) {
		this.adobeTicket = adobeTicket;
	}

	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.httpcommons.authorization.HttpSigner#sign(org.apache.http.HttpRequest)
	 */
	@Override
	public void sign(HttpRequest request) {
		if(adobeTicket != null)
			request.setHeader("Authorization",ADOBE_TICKET_PARAM + "=" + "\"" + adobeTicket + "\"");
		else
			throw new IllegalStateException("Adobe authorization ticket was not set");
	}
}
