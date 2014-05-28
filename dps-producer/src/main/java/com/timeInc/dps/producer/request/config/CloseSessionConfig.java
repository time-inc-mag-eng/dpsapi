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

import com.timeInc.dps.translator.TranslatableRequest;

/**
 * The close session request. See Adobe DPS folio documentation for more information.
 */
public class CloseSessionConfig implements TranslatableRequest {
	private final boolean cancelToken;
	
	/**
	 * Instantiates a new close session config.
	 */
	public CloseSessionConfig() {
		cancelToken = false;
	}
	
	/**
	 * Instantiates a new close session config.
	 *
	 * @param cancelToken the cancel token
	 */
	public CloseSessionConfig(boolean cancelToken) {
		this.cancelToken = cancelToken;
	}

	/**
	 * Checks if is cancel token.
	 *
	 * @return true, if is cancel token
	 */
	public boolean isCancelToken() {
		return cancelToken;
	}
}
