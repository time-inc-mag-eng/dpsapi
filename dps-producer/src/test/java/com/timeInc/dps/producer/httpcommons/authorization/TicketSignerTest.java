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

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpGet;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import static org.junit.Assert.*;



import org.junit.Test;

import com.timeInc.dps.producer.httpcommons.authorization.TicketSigner;

public class TicketSignerTest {
	private final HttpRequest request = new HttpGet("anaddress");

	private static final String ADOBE_TICKET = "RANDOMTICKET314159";

	private final TicketSigner signer = new TicketSigner(ADOBE_TICKET);
	
	private static final String authHeaderValue = TicketSigner.ADOBE_TICKET_PARAM + "=\"" + ADOBE_TICKET + "\""; 

	@Test
	public void putsSignatureInCorrectHeader() {
		signer.sign(request);
		
		Header authHeader = request.getFirstHeader(TicketSigner.AUTH_HEADER);
		assertThat(authHeader,is(notNullValue()));
	}
	
	@Test
	public void correctlySignsRequestUsingSpecifiedTicket() {
		signer.sign(request);
		
		Header authHeader = request.getFirstHeader(TicketSigner.AUTH_HEADER);
		
		String authValue = authHeader.getValue();
		
		assertThat(authValue,equalTo(authHeaderValue));
	}
}
