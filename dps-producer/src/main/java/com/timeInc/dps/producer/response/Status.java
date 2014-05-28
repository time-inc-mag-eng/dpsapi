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

import com.timeInc.dps.translator.TranslatableResponse;


/**
 * The status response. All response classes inherit from this class if it contains a status field.
 * The status response contains the status of the request that was made, the new download ticket if any,
 * the new ticket if any, and the error detail if the request failed.
 */
public class Status implements TranslatableResponse {
	
	/** The success. */
	public static String SUCCESS = "ok";
	
	
	private final String status;
	private final String downloadTicket;
	private final String ticket;
	private final String errorDetail;
	
	
	/**
	 * Instantiates a new status.
	 *
	 * @param status the status
	 * @param ticket the ticket
	 * @param downloadTicket the download ticket
	 */
	public Status(String status, String ticket, String downloadTicket) {
		this(status,ticket,downloadTicket,null);
	}
	
	
	Status(String status, String ticket, String downloadTicket, String errorDetail) {
		this.status = status;
		this.downloadTicket = downloadTicket;
		this.ticket = ticket;
		this.errorDetail = errorDetail;
	}
	
	
	/**
	 * Instantiates a new status.
	 *
	 * @param status the status
	 */
	public Status(String status) {
		this(status,null,null);
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
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
	 * Gets the download ticket.
	 *
	 * @return the download ticket
	 */
	public String getDownloadTicket() {
		return downloadTicket;
	}
	
	/**
	 * Gets the error detail.
	 *
	 * @return the error detail
	 */
	public String getErrorDetail() {
		return errorDetail;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) { return EqualsBuilder.reflectionEquals(this, obj); }
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() { return HashCodeBuilder.reflectionHashCode(this); }
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() { return ToStringBuilder.reflectionToString(this); }
	
}
