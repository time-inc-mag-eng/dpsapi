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
package com.timeInc.dps.translator;

import org.apache.log4j.Logger;
import com.google.gson.Gson;

/**
 * Converts a json string to a TranslatableResponse objects, vice versa for a TranslatableRequest.
 */
public class JsonTranslator implements RequestTranslator, ResponseTranslator {
	private final Gson gson;
	
	private static final Logger log = Logger.getLogger(JsonTranslator.class);
	
	/**
	 * Instantiates a new json translator.
	 *
	 * @param gson the gson
	 */
	public JsonTranslator(Gson gson) {
		this.gson = gson;
	}
	
	/**
	 * Instantiates a new json translator.
	 */
	public JsonTranslator() { 
		this(new Gson());
	}
	
	/* (non-Javadoc)
	 * @see com.timeInc.dps.translator.RequestTranslator#convertToString(com.timeInc.dps.translator.TranslatableRequest)
	 */
	@Override
	public <T extends TranslatableRequest> String convertToString(T object) {
		String json = gson.toJson(object);
		log.debug("Converted request to json:" + json);
		return json;
	}

	/* (non-Javadoc)
	 * @see com.timeInc.dps.translator.ResponseTranslator#convertToObject(java.lang.Class, java.lang.String)
	 */
	@Override
	public <T extends TranslatableResponse> T convertToObject(Class<T> clazz, String json) {
		log.debug("json:" + json);

		T object = gson.fromJson(json,clazz);
		
		return object;
	}
}
