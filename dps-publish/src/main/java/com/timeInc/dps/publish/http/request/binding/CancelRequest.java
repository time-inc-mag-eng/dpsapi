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

import com.timeInc.dps.publish.request.config.TicketConfig;
import com.timeInc.dps.publish.response.Result;


/**
 * The http metadata associated with a cancel publish request/response
 * see Adobe Publishing API documentation for more information.
 */
public class CancelRequest extends AbstractPublishRequest<Result> {
	private final TicketConfig config;
	
	/**
	 * Instantiates a new cancel request.
	 *
	 * @param basePath the base path
	 * @param config the config
	 */
	public CancelRequest(String basePath, TicketConfig config) {
		super(basePath);
		this.config = config;
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
	public TicketConfig getConfig() {
		return config;
	}

	@Override
	protected String getRelativePath() {
		return "publishRequests/" + config.getRequestId() + "/cancel";
	}

}
