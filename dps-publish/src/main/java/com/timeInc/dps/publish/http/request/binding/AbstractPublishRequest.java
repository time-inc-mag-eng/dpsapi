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

import com.timeInc.dps.http.request.AbstractHttpRequest;
import com.timeInc.dps.publish.response.Result;

/**
 * Base class for http metadata that is associated with a publish request/response
 * Uses {@link AbstractPublishRequest#SERVICE_PATH} as the the service path.
 *
 * @param <T> the response of subtype Result
 */
public abstract class AbstractPublishRequest<T extends Result> extends AbstractHttpRequest<T>  {
	public static final String SERVICE_PATH = "ddp/issueServer";
	
	protected AbstractPublishRequest(String basePath) {
		this(basePath,SERVICE_PATH);
	}
	
	protected AbstractPublishRequest(String basePath,String servicePath) {
		super(basePath,servicePath);
	}
}

