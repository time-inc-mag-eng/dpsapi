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
package com.timeInc.dps.producer.request.config;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.validator.EmailValidator;

import com.timeInc.dps.translator.TranslatableRequest;


/**
 * The open session request. See Adobe DPS folio documentation for more information.
 */
@SuppressWarnings("deprecation")
public class OpenSessionConfig implements TranslatableRequest {
	private final String email;
	private final String password;
	private final String authToken;
	private final boolean needToken;
	
	/**
	 * Instantiates a new open session config.
	 *
	 * @param email the email
	 * @param password the password
	 * @param needToken the need token
	 */
	public OpenSessionConfig(String email, String password, boolean needToken) {
		if(!EmailValidator.getInstance().isValid(email))
			throw new IllegalArgumentException("Invalid email format");
		
		this.email = email;
		this.password = password;
		this.authToken = null;
		this.needToken = needToken;
	}

	/**
	 * Instantiates a new open session config.
	 *
	 * @param authToken the auth token
	 * @param needToken the need token
	 */
	public OpenSessionConfig(String authToken, boolean needToken) {
		this.authToken = authToken;
		
		this.email = null;
		this.password = null;
		this.needToken = needToken;
		
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Need token.
	 *
	 * @return true, if successful
	 */
	public boolean needToken() {
		return needToken;
	}

	/**
	 * Gets the auth token.
	 *
	 * @return the auth token
	 */
	public String getAuthToken() {
		return authToken;
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
	
}
