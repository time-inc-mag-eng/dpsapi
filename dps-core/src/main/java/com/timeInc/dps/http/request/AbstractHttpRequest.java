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
package com.timeInc.dps.http.request;

import java.net.MalformedURLException;
import java.net.URL;

import java.lang.reflect.ParameterizedType;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.validator.routines.UrlValidator;

import com.timeInc.dps.translator.TranslatableResponse;

/**
 * A skeletal implementation of {@code HttpRequestMapper} that 
 * uses a base address and service path for the base url for a TranslatableRequest.
 * Uses the generic parameter T as the response class; so nested generic types are
 * unsupported in a type-safe manner.
 * 
 * 
 * @param <T> the TranslatableResponse type
 */
public abstract class AbstractHttpRequest<T extends TranslatableResponse> implements HttpRequestMapper<T> {
	private final String servicePath;
	private final Class<T> responseCls;
	
	protected String baseAddress;
	
	/**
	 * Instantiates a new abstract http request.
	 *
	 * @param baseAddress the base address i.e. https://api2.digitalpublishing.acrobat.com
	 * @param servicePath the service path i.e. /webservices/sessions
	 */
	@SuppressWarnings("unchecked")
	public AbstractHttpRequest(String baseAddress, String servicePath) {
		if(baseAddress != null)		
			this.baseAddress = checkAndNormalizeAddress(baseAddress);
		
		this.servicePath = servicePath;
		
		this.responseCls = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass() ).getActualTypeArguments()[0];
	}
	
	protected AbstractHttpRequest(String servicePath) {
		this(null,servicePath);
	}
	
		
	protected static String checkAndNormalizeAddress(String baseAddress) {
		try {
			checkIfValidAddress(baseAddress);
			return removeTrailingSlash(baseAddress);
		} catch (MalformedURLException e) {
			throw new AssertionError("This should never be reached");
		}
	}

	private static void checkIfValidAddress(String baseAddress) throws MalformedURLException {
		String[] schemes = {"http","https"};
		UrlValidator httpValidator = new UrlValidator(schemes);

		if(httpValidator.isValid(baseAddress))
			checkForQuery(baseAddress);
		else 
			throw new IllegalArgumentException("Not a valid http/https address");

	}

	private static void checkForQuery(String baseAddress) throws MalformedURLException {
		URL url = new URL(baseAddress);
		if(url.getQuery() != null) 
			throw new IllegalArgumentException("Base Address can not have a query");
	}
	
	/* (non-Javadoc)
	 * @see com.timeInc.dps.http.request.HttpRequestMapper#getResponseClass()
	 */
	@Override
	public Class<T> getResponseClass() {
		return responseCls;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) { return EqualsBuilder.reflectionEquals(this, obj); }
	

	/* (non-Javadoc)
	 * @see com.timeInc.dps.http.request.HttpRequestMapper#getAbsolutePath()
	 */
	@Override
	public String getAbsolutePath() { 
		String folioPathWithBaseAddress = addPathToBaseAddress(servicePath,getRelativePath());
		return removeTrailingSlash(folioPathWithBaseAddress);
	}
	
	protected abstract String getRelativePath();
	
	private String addPathToBaseAddress(String... paths) {
		String pathWithSlashes = "";
		
		for(String path : paths) {
			pathWithSlashes = pathWithSlashes + addSlashes(path);
		}

		return baseAddress + pathWithSlashes.replaceAll("//","/");
		
	}
	
	private static String addSlashes(String path) {
		if(path.charAt(0) != '/')
			path = "/" + path;
		
		if(path.charAt(path.length()-1) != '/')
			path = path + "/";
		
		return path;
	}
	
	private static String removeTrailingSlash(String baseUrl) {
		return baseUrl.charAt(baseUrl.length()-1) == '/' ? baseUrl.substring(0,baseUrl.length()-1) : baseUrl;
	}
}
