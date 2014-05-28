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
package com.timeInc.dps.producer.request.model;

import org.junit.Test;

import com.timeInc.dps.producer.request.config.OpenSessionConfig;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class OpenSessionConfigTest {

	@Test(expected = IllegalArgumentException.class)
	public void checksEmailValidility() {
		new OpenSessionConfig("test.@com","password",false); // construct an instance with an invalid email
	}
	
	@Test
	public void requiredFieldsAreSetForEmail() {		
		String email = "test@t.com";
		String password = "password";
		boolean needToken = false;
		
		OpenSessionConfig request = new OpenSessionConfig(email,password,needToken);
		
		assertThat("Email",request.getEmail(),equalTo(email));
		assertThat("Password",request.getPassword(),equalTo(password));
		assertThat("Token",request.needToken(),equalTo(needToken));
	}
	
	@Test
	public void requiredFieldsAreSetForAuthToken() {
		String token = "sometoken";
		boolean needToken = false;
		
		OpenSessionConfig request = new OpenSessionConfig(token,needToken);
		
		assertThat("Email",request.getAuthToken(),equalTo(token));	
		assertThat("Token",request.needToken(),equalTo(needToken));
		
	}	
}
