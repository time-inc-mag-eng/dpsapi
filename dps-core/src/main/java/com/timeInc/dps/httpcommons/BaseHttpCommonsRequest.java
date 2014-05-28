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

import java.nio.charset.Charset;

import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;

import com.timeInc.dps.translator.RequestTranslator;
import com.timeInc.dps.translator.TranslatableRequest;

/**
 * Skeletal implementation of {@code HttpCommonsRequest} that converts 
 * a TranslatableRequest parameter into a string using the specified RequestTranslator.
 */
public abstract class BaseHttpCommonsRequest implements HttpCommonsRequest {
	protected static final Charset ENCODING = Charset.forName("UTF-8");
	
	protected TranslatableRequest parameter;
	protected final RequestTranslator translator;
	
	/**
	 * Instantiates a new base http commons request.
	 *
	 * @param translator the translator
	 */
	public BaseHttpCommonsRequest(RequestTranslator translator) {
		this.translator = translator;
	}
	
	/* (non-Javadoc)
	 * @see com.timeInc.dps.httpcommons.HttpCommonsRequest#generateRequest(java.lang.String)
	 */
	@Override
	public HttpUriRequest generateRequest(String path) {
		HttpRequestBase request = getRequest(path);
		addParameter(request);
		return request;
	}
	
	private final void addParameter(HttpRequestBase request) {
		if(parameter != null) {
			addTranslatedRequest(request, translator.convertToString(parameter));
		}
	}
	
	abstract void addTranslatedRequest(HttpRequestBase request, String translatedRequest);
	abstract HttpRequestBase getRequest(String path);

	
	/* (non-Javadoc)
	 * @see com.timeInc.dps.httpcommons.HttpCommonsRequest#setParameter(com.timeInc.dps.translator.TranslatableRequest)
	 */
	@Override
	public void setParameter(TranslatableRequest parameter) {
		this.parameter = parameter;
	}
}
