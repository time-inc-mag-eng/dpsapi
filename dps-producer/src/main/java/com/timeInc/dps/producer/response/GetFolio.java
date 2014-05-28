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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * The get folio response. See Adobe DPS folio documentation for more information.
 */
public class GetFolio extends Status {
	private final FolioInfo folio;

	/**
	 * Instantiates a new gets the folio.
	 *
	 * @param folio the folio
	 * @param status the status
	 */
	public GetFolio(FolioInfo folio, String status) {
		super(status);
		this.folio = folio;

	}
	
	/**
	 * Gets the folio.
	 *
	 * @return the folio
	 */
	public FolioInfo getFolio() {
		return folio;
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
	
	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.response.Status#toString()
	 */
	@Override
	public String toString() { return ToStringBuilder.reflectionToString(this); }

}
