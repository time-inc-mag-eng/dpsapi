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

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

/**
 * Converts a TranslatableRequest object to a http query string.
 * Fields marked as transient will not be included.
 */
public class HttpParameterTranslator implements RequestTranslator {
	private static final String PARAM_ENCODING = "UTF-8";
	private static final Logger log = Logger.getLogger(HttpParameterTranslator.class);
	
	/* (non-Javadoc)
	 * @see com.timeInc.dps.translator.RequestTranslator#convertToString(com.timeInc.dps.translator.TranslatableRequest)
	 */
	@Override
	public <T extends TranslatableRequest> String convertToString(T object)  {
		String query = gatherQueryForClassHierarchy(object);
		log.debug("Converted to:" + query);
		return query;
	}

	private static String gatherQueryForClassHierarchy(Object objectToTranslate) {
		Class<?> currentClass = objectToTranslate.getClass();

		StringBuilder out = new StringBuilder();

		while(currentClass!=null) {
			Field fields[] = currentClass.getDeclaredFields();
			String queryForCurrentClass  = getHttpQueryForFields(fields,objectToTranslate);
			out.insert(0,queryForCurrentClass);

			currentClass = currentClass.getSuperclass(); // move to parent class
		}

		return removeLeadingAmpersand(out.toString());
	}

	private static String getHttpQueryForFields(Field[] fields,Object objectToTranslate) {
		try {
			return generateHttpQueryString(fields,objectToTranslate);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private static String generateHttpQueryString(Field[] fields,Object objectToTranslate) throws IllegalAccessException {
		StringBuilder sb  = new StringBuilder();
		for(Field field : fields) {

			field.setAccessible(true);
			if(!Modifier.isTransient(field.getModifiers())) {
				Object obj = field.get(objectToTranslate);
				if(obj != null) {
					sb.append("&" + encodeString(field.getName()));
					sb.append("=" + encodeString(obj.toString()));
				}
			}


		}
		return sb.toString();
	}

	private static String encodeString(String toEncode) {
		try {
			return URLEncoder.encode(toEncode,PARAM_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new AssertionError("This should never happen");
		}
	}

	private static String removeLeadingAmpersand(String query) {
		if(!query.isEmpty() && query.charAt(0) == '&') 
			return query.substring(1);
		return query;
	}
}
