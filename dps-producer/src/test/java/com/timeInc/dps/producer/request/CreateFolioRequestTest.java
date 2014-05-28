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
package com.timeInc.dps.producer.request;

import static org.junit.Assert.*;
import org.junit.Test;

import com.timeInc.dps.producer.http.request.binding.CreateFolioRequest;
import com.timeInc.dps.producer.response.CreateFolio;

import static org.hamcrest.Matchers.*;


public class CreateFolioRequestTest {

	String BASE_ADDRESS = "http://www.test.com/";
	String TICKET = "abcdefg";
	
	CreateFolioRequest request = new CreateFolioRequest(BASE_ADDRESS,TICKET,null);
	
	@Test
	public void returnsCorrectResponseType() {
		assertThat(request.getResponseClass(),equalTo(CreateFolio.class));
	}
	
	@Test 
	public void correctHttpPath() {
		assertThat(request.getAbsolutePath(),equalTo(BASE_ADDRESS+"webservices/folios"));
	}
	
	@Test
	public void ticketMatches() {
		assertThat(request.getTicket(),equalTo(TICKET));
	}
}
