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

import java.net.URISyntaxException;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;

import com.timeInc.dps.translator.RequestTranslator;

/**
 * A Http Get request that uses the query string as the translated parameter
 */
public class HttpGetRequest extends BaseHttpCommonsRequest {
	
	/**
	 * Instantiates a new http get request.
	 *
	 * @param translator the translator which must conform to the semantics of a Http query string
	 */
	public HttpGetRequest(RequestTranslator translator) {
		super(translator);
	}
	
	@Override
	protected HttpRequestBase getRequest(String path) {
		return new HttpGet(path);
	}

	@Override
	protected void addTranslatedRequest(HttpRequestBase request, String parameter) {
		URIBuilder builder = new URIBuilder(request.getURI());
		builder.setQuery(parameter);
		try {
			request.setURI(builder.build());
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("Invalid URI",e);
		}	
	}
}
