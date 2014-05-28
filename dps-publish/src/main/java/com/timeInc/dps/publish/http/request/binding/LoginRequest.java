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
package com.timeInc.dps.publish.http.request.binding;

import com.timeInc.dps.publish.request.config.LoginConfig;
import com.timeInc.dps.publish.response.Credential;

/**
 * The http metadata associated with a sign in request/response
 * see Adobe Publishing API documentation for more information.
 */
public class LoginRequest extends AbstractPublishRequest<Credential> {
	private final LoginConfig info;

	private static final String PATH = "signInWithCredentials";
	
	/**
	 * Instantiates a new login request.
	 *
	 * @param basePath the base path
	 * @param info the info
	 */
	public LoginRequest(String basePath, LoginConfig info) {
		super(basePath);
		this.info = info;
	}

	/* (non-Javadoc)
	 * @see com.timeInc.dps.http.request.HttpRequestMapper#getMethod()
	 */
	@Override
	public Method getMethod() {
		return Method.POST;
	}

	/* (non-Javadoc)
	 * @see com.timeInc.dps.http.request.HttpRequestMapper#getConfig()
	 */
	@Override
	public LoginConfig getConfig() {
		return info;
	}

	@Override
	protected String getRelativePath() {
		return PATH;
	}
	
}
