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
package com.timeInc.dps.producer.response;

import org.apache.commons.lang3.builder.*;


/**
 * The open session response. See Adobe DPS folio documentation for more information.
 */
public class OpenSession extends Server {
	private String authToken;
	private boolean verificationRequired;
	private int verificationGraceDays;


	/**
	 * Instantiates a new open session.
	 *
	 * @param authToken the auth token
	 * @param status the status
	 * @param ticket the ticket
	 * @param downloadTicket the download ticket
	 * @param server the server
	 * @param downloadServer the download server
	 * @param verificationRequired the verification required
	 * @param verificationGraceDays the verification grace days
	 */
	public OpenSession(String authToken, String status,
			String ticket, String downloadTicket,
			String server, String downloadServer, boolean verificationRequired,
			int verificationGraceDays) {

		super(status,ticket,downloadTicket,downloadServer,server);
		this.authToken = authToken;
		this.verificationRequired = verificationRequired;
		this.verificationGraceDays = verificationGraceDays;
	}
	
	/**
	 * Gets the auth token.
	 *
	 * @return the auth token
	 */
	public String getAuthToken() {
		return authToken;
	}


	/**
	 * Checks if is verification required.
	 *
	 * @return true, if is verification required
	 */
	public boolean isVerificationRequired() {
		return verificationRequired;
	}


	/**
	 * Gets the verification grace days.
	 *
	 * @return the verification grace days
	 */
	public int getVerificationGraceDays() {
		return verificationGraceDays;
	}

	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.response.Server#equals(java.lang.Object)
	 */
	@Override
    public boolean equals(Object obj) { return EqualsBuilder.reflectionEquals(this, obj); }
    
    /* (non-Javadoc)
     * @see com.timeInc.dps.producer.response.Server#hashCode()
     */
    @Override
    public int hashCode() { return HashCodeBuilder.reflectionHashCode(this); }		

}
