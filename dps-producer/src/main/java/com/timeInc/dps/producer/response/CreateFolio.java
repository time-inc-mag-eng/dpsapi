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
 * The create folio response. See Adobe DPS folio documentation for more information.
 */
public class CreateFolio extends Status {
	private final String folioID;

	/**
	 * Instantiates a new creates the folio.
	 *
	 * @param folioID the folio id
	 * @param status the status
	 */
	public CreateFolio(String folioID, String status) {
		super(status);
		this.folioID = folioID;

	}

	/**
	 * Gets the folio id.
	 *
	 * @return the folio id
	 */
	public String getFolioID() {
		return folioID;
	}


	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.response.Status#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) { return EqualsBuilder.reflectionEquals(this, obj); }
	
	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.response.Status#hashCode()
	 */
	@Override
	public int hashCode() { return HashCodeBuilder.reflectionHashCode(this); }	
}
