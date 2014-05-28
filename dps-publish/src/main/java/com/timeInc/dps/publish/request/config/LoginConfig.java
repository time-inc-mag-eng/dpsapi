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
 * Request for signing in the user in to the Distribution Server
 * using their Adobe ID and password.
 * 
 * See Adobe Publishing API documentation for more information.
 *
 */

public class LoginConfig extends IdentifierConfig {
	private final String emailAddress;
	private final String password;

	/**
	 * Constructs an instance using the specified credentials.
	 *
	 * @param emailAddress E-mail address associated with the account.
	 * @param password Password associated with the account.
	 */
	public LoginConfig(String emailAddress, String password) {
		super();
		this.emailAddress = emailAddress;
		this.password = password;
	}


	/**
	 * Gets the email address.
	 *
	 * @return the email address
	 */
	public String getEmailAddress() {
		return emailAddress;
	}


	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoginConfig [emailAddress=" + emailAddress + ", password="
				+ password + "]";
	}
	
}
