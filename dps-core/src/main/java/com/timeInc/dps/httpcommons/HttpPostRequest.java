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

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;

import com.timeInc.dps.translator.RequestTranslator;

/**
 * A http post request where the body is the converted TranslatableRequest
 */
public class HttpPostRequest extends BaseHttpCommonsRequest  {
	private final boolean body;
	
	/**
	 * Instantiates a new http post request.
	 *
	 * @param translator the translator
	 */
	public HttpPostRequest(RequestTranslator translator) {
		this(translator,true);
	}
	
	/**
	 * Instantiates a new http post request.
	 *
	 * @param translator the translator
	 * @param body the body
	 */
	public HttpPostRequest(RequestTranslator translator, boolean body) {
		super(translator);
		this.body = body;
	}

	@Override
	protected void addTranslatedRequest(HttpRequestBase request, String parameter) {
		HttpPost post = (HttpPost) request;
		if(body) {
			
			post.setHeader("Content-Type",getContentType());
			StringEntity requestBodyEntity;
			requestBodyEntity = new StringEntity(parameter,ENCODING);
			post.setEntity(requestBodyEntity);	
		}
	}

	@Override
	protected HttpRequestBase getRequest(String path) {
		return new HttpPost(path);
	}
	
	protected String getContentType() { // TODO tie it to request translator
		return "application/json";
	}
	
}
