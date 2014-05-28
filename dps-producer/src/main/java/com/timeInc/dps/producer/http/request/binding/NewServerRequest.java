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

import com.timeInc.dps.producer.http.request.ProducerVisitor;
import com.timeInc.dps.producer.http.request.TicketRequest;
import com.timeInc.dps.producer.response.Server;
import com.timeInc.dps.translator.TranslatableRequest;

/**
 * The http metadata associated with a new server request/response
 * see Adobe DPS documentation for more information.
 */
public class NewServerRequest extends AbstractProducerRequest<Server> implements TicketRequest<Server> {
	private String ticket;
	
	/**
	 * Instantiates a new new server request.
	 *
	 * @param baseAddress the base address
	 * @param ticket the ticket
	 */
	public NewServerRequest(String baseAddress, String ticket) {
		this(baseAddress);
		this.ticket = ticket;
	}
	
	/**
	 * Instantiates a new new server request.
	 *
	 * @param baseAddress the base address
	 */
	public NewServerRequest(String baseAddress) {
		super(baseAddress);
	}


	/* (non-Javadoc)
	 * @see com.timeInc.dps.http.request.HttpRequestMapper#getConfig()
	 */
	@Override
	public TranslatableRequest getConfig() {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.timeInc.dps.http.request.HttpRequestMapper#getMethod()
	 */
	@Override
	public Method getMethod() {
		return Method.GET;
	}

	@Override
	protected ProducerPath getFolioPath() {
		return ProducerPath.newServer();
	}


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
	 * @see com.timeInc.dps.producer.http.request.binding.AbstractProducerRequest#accept(com.timeInc.dps.producer.http.request.ProducerVisitor)
	 */
	@Override
	public <V> V accept(ProducerVisitor<V> visitor) {
		return visitor.visit((TicketRequest<?>)this);
	}
}
