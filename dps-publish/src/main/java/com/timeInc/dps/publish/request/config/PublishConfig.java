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
 * Configuration for publishing or updating the specified folio from the folio 
 * producer workflow into the distribution service.
 * 
 * See Adobe Publishing API documentation for more information.
 *
 */

public class PublishConfig extends IdentifierConfig {
	private final transient String folioId;
	
	private final State state;
	private final String productId;
	private final Boolean retail;
	private final boolean updateContents;
	private final Long onSaleDate; 
	private final String ticket;

	
	/**
	 * The Enum State.
	 */
	public enum State {
		PUBLIC("public"), PRIVATE("private");
		
		private final String shortName;
		
		private State(String shortName) {
			this.shortName = shortName;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return shortName;
		}
	}
	
	
	/**
	 * Constructs a private publish configuration.
	 *
	 * @param foloId The unique folio identifier to publish.
	 * @param ticket Authentication ticket returned from distribution server sign-in.
	 * @param productId A unique reverse-DNS style string (for example, com.adobe.magazineTitle.january. May only contain characters: a-z, A-Z, 0-9, and _.
	 * @param updateContents True to update changed articles and images; false to update only published folio metadata.
	 * @return A configuration object representing the passed in settings.
	 */
	public static PublishConfig getPrivateInstance(String foloId, String ticket, String productId, boolean updateContents) {
		return new PublishConfig(foloId, ticket, false, productId, updateContents, State.PRIVATE, null);
	}
	
	/**
	 * Gets the scheduled public instance.
	 *
	 * @param foloId the folo id
	 * @param ticket the ticket
	 * @param productId the product id
	 * @param updateContents the update contents
	 * @param retail the retail
	 * @param saleDateEpoch the sale date epoch
	 * @return the scheduled public instance
	 */
	public static PublishConfig getScheduledPublicInstance(String foloId, String ticket, String productId, boolean updateContents,
			boolean retail, long saleDateEpoch) {
		return new PublishConfig(foloId, ticket, retail, productId, updateContents, State.PUBLIC, saleDateEpoch);
	}
	
	
	/**
	 * Constructs a public publish configuration.
	 *
	 * @param folioId the folio id
	 * @param ticket Authentication ticket returned from distribution server sign-in.
	 * @param productId A unique reverse-DNS style string (for example, com.adobe.magazineTitle.january. May only contain characters: a-z, A-Z, 0-9, and _.
	 * @param updateContents True to update changed articles and images; false to update only published folio metadata.
	 * @param retail True for paid content; false for free content.
	 * @return A configuration object representing the passed in settings.
	 */
	public static PublishConfig getPublicInstance(String folioId, String ticket, String productId, boolean updateContents, boolean retail) {
		return new PublishConfig(folioId, ticket, retail, productId, updateContents, State.PUBLIC, null);
	}
	
	
	private PublishConfig(String folioId, String ticket, boolean retail, String productId,
			boolean updateContents, State state, Long onSaleDate) {
		super();
		this.folioId = folioId;
		this.ticket = ticket;
		this.retail = retail;
		this.productId = productId;
		this.updateContents = updateContents;
		this.state = state;
		this.onSaleDate = onSaleDate;
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
	 * Checks if is retail.
	 *
	 * @return true, if is retail
	 */
	public boolean isRetail() {
		return retail;
	}
	
	/**
	 * Gets the product id.
	 *
	 * @return the product id
	 */
	public String getProductId() {
		return productId;
	}
	
	/**
	 * Checks if is update contents.
	 *
	 * @return true, if is update contents
	 */
	public boolean isUpdateContents() {
		return updateContents;
	}
	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public State getState() {
		return state;
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
