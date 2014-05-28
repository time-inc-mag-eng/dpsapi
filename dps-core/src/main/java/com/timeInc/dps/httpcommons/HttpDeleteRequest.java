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
package com.timeInc.dps.httpcommons;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpRequestBase;

import com.timeInc.dps.translator.RequestTranslator;

/**
 * A Http Delete request that is not allowed to have a parameter.
 */
public class HttpDeleteRequest extends BaseHttpCommonsRequest {	
	
	/**
	 * Instantiates a new http delete request.
	 *
	 * @param translator the translator
	 */
	public HttpDeleteRequest(RequestTranslator translator) {
		super(translator);
	}
	
	@Override
	protected HttpRequestBase getRequest(String path) {
		return new HttpDelete(path);
	}

	@Override
	protected void addTranslatedRequest(HttpRequestBase request, String translatedRequest) {
		throw new UnsupportedOperationException("Http Delete can not have parameters");
	}
	
}
