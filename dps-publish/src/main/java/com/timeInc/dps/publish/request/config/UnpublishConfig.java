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
 * Request for unpublishing a folio.
 * See Adobe Publishing API documentation for more information.
 */
public class UnpublishConfig extends IdentifierConfig {
	private final String ticket;
	private final transient String folioId;
	
	/**
	 * Instantiates a new unpublish config.
	 *
	 * @param ticket the ticket
	 * @param folioId the folio id
	 */
	public UnpublishConfig(String ticket, String folioId) {
		super();
		this.ticket = ticket;
		this.folioId = folioId;
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
	 * Gets the folio id.
	 *
	 * @return the folio id
	 */
	public String getFolioId() {
		return folioId;
	}
}
