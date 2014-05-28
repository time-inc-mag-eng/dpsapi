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

import com.timeInc.dps.httpcommons.HttpPostRequest;
import com.timeInc.dps.translator.RequestTranslator;

class HttpPublishPostRequest extends HttpPostRequest {

	/**
	 * Instantiates a new http publish post request.
	 *
	 * @param translator the translator
	 */
	public HttpPublishPostRequest(RequestTranslator translator) {
		super(translator);
	}

	@Override
	protected String getContentType() { // TODO move contenttype to requesttranslator
		return "application/x-www-form-urlencoded";
	}
}
