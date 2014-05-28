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
package com.timeInc.dps.producer.http.request.binding;

import com.timeInc.dps.producer.http.request.ModifiableTicketRequest;
import com.timeInc.dps.producer.http.request.ProducerVisitor;
import com.timeInc.dps.producer.http.request.TicketRequest;
import com.timeInc.dps.producer.response.Status;

/**
 * Represents a request that has a ticket associated with it.
 * @see AbstractProducerRequest
 *
 */
public abstract class AbstractModifiableTicketRequest<T extends Status> extends AbstractProducerRequest<T> implements ModifiableTicketRequest<T> {
	private String ticket;

	/**
	 * Instantiates a new abstract modifiable ticket request.
	 *
	 * @param baseAddress the base address
	 * @param ticket the ticket
	 */
	public AbstractModifiableTicketRequest(String baseAddress, String ticket) {
		super(baseAddress);
		this.ticket = ticket;
	}
	
	/**
	 * Instantiates a new abstract modifiable ticket request.
	 */
	public AbstractModifiableTicketRequest() {}	
	

	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.http.request.TicketRequest#getTicket()
	 */
	@Override
	public String getTicket() {
		return ticket;
	}
	
	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.http.request.TicketRequest#setTicket(java.lang.String)
	 */
	@Override
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.http.request.ModifiableTicketRequest#setBasePath(java.lang.String)
	 */
	@Override
	public void setBasePath(String path) {
		this.baseAddress = checkAndNormalizeAddress(path);
	}
	
	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.http.request.binding.AbstractProducerRequest#accept(com.timeInc.dps.producer.http.request.ProducerVisitor)
	 */
	@Override
	public <V> V accept(ProducerVisitor<V> visitor) {
		return visitor.visit((TicketRequest<?>)this);
	}
}
