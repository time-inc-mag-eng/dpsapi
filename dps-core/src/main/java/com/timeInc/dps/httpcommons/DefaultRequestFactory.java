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

import java.io.File;

import com.timeInc.dps.translator.RequestTranslator;
import com.timeInc.dps.translator.TranslatableRequest;

/**
 * A factory for creating various {@code HttpCommonsRequest} objects for most http methods.
 */
public class DefaultRequestFactory implements AbstractRequestFactory {
	protected final RequestTranslator translator;
	
	/**
	 * Instantiates a new default request factory.
	 *
	 * @param translator the translator
	 */
	public DefaultRequestFactory(RequestTranslator translator) {
		this.translator = translator;
	}
	
	
	/* (non-Javadoc)
	 * @see com.timeInc.dps.httpcommons.AbstractRequestFactory#getMethod(com.timeInc.dps.translator.TranslatableRequest)
	 */
	@Override
	public HttpCommonsRequest getMethod(TranslatableRequest request) {
		HttpCommonsRequest factory = new HttpGetRequest(translator);
		setParameter(factory,request);

		return factory;
	}

	/* (non-Javadoc)
	 * @see com.timeInc.dps.httpcommons.AbstractRequestFactory#postMethod(com.timeInc.dps.translator.TranslatableRequest)
	 */
	@Override
	public HttpCommonsRequest postMethod(TranslatableRequest request) {
		HttpCommonsRequest factory = new HttpPostRequest(translator);;
		setParameter(factory,request);

		return factory;
	}

	/* (non-Javadoc)
	 * @see com.timeInc.dps.httpcommons.AbstractRequestFactory#multiPartMethod(com.timeInc.dps.translator.TranslatableRequest, java.io.File)
	 */
	@Override
	public HttpCommonsRequest multiPartMethod(TranslatableRequest request, File file) {
		HttpCommonsRequest factory = new HttpMultiPartRequest(translator,file);
		setParameter(factory,request);

		return factory;
	}

	/* (non-Javadoc)
	 * @see com.timeInc.dps.httpcommons.AbstractRequestFactory#deleteMethod(com.timeInc.dps.translator.TranslatableRequest)
	 */
	@Override
	public HttpCommonsRequest deleteMethod(TranslatableRequest request) {
		HttpCommonsRequest factory = new HttpDeleteRequest(translator);
		setParameter(factory,request);

		return factory;
	}
	
	protected static void setParameter(HttpCommonsRequest factory, TranslatableRequest request) {
		factory.setParameter(request);
	}
}
