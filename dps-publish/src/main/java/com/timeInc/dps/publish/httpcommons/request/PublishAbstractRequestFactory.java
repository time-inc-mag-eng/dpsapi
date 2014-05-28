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
package com.timeInc.dps.publish.httpcommons.request;

import com.timeInc.dps.httpcommons.DefaultRequestFactory;
import com.timeInc.dps.httpcommons.HttpCommonsRequest;
import com.timeInc.dps.translator.RequestTranslator;
import com.timeInc.dps.translator.TranslatableRequest;

public class PublishAbstractRequestFactory extends DefaultRequestFactory {

	/**
	 * Instantiates a new publish abstract request factory.
	 *
	 * @param translator the translator
	 */
	public PublishAbstractRequestFactory(RequestTranslator translator) {
		super(translator);
	}
	
	/* (non-Javadoc)
	 * @see com.timeInc.dps.httpcommons.DefaultRequestFactory#postMethod(com.timeInc.dps.translator.TranslatableRequest)
	 */
	@Override
	public HttpCommonsRequest postMethod(TranslatableRequest request) {
		HttpCommonsRequest factory = new HttpPublishPostRequest(translator);;
		setParameter(factory, request);

		return factory;
	}
}
