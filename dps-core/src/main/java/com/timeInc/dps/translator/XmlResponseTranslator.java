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
 * Converts an xml response to a TranslatableResponse object.
 */
public class XmlResponseTranslator implements ResponseTranslator {
	private final Gson gson;

	private final Logger log = Logger.getLogger(XmlResponseTranslator.class);
	
	/**
	 * Instantiates a new xml response translator.
	 */
	public XmlResponseTranslator() {
		this(new Gson());
	}
	
	/**
	 * Instantiates a new xml response translator.
	 *
	 * @param gson the gson
	 */
	public XmlResponseTranslator(Gson gson) {
		this.gson = gson;
	}
	
	
	/* (non-Javadoc)
	 * @see com.timeInc.dps.translator.ResponseTranslator#convertToObject(java.lang.Class, java.lang.String)
	 */
	@Override
	public <T extends TranslatableResponse> T convertToObject(Class<T> clazz, String response) {
		
		log.debug("Converting xml: " + response);
		
		String json = XmlUtil.toJson(response);
		
		log.debug("Converted xml to json: " + json);
		
		return gson.fromJson(json,clazz);
	}
}
