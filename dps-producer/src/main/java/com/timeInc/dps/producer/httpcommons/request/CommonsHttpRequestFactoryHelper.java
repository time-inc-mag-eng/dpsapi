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
package com.timeInc.dps.producer.httpcommons.request;

import com.timeInc.dps.producer.httpcommons.authorization.HttpSigner;
import com.timeInc.dps.producer.httpcommons.authorization.OAuthSigner;
import com.timeInc.dps.producer.httpcommons.authorization.TicketSigner;
import com.timeInc.dps.producer.translator.GsonProducerFactory;
import com.timeInc.dps.translator.HttpParameterTranslator;
import com.timeInc.dps.translator.JsonTranslator;
import com.timeInc.dps.translator.RequestTranslator;

/**
 * A factory class that contains that provides the necessary dependencies
 * that are needed to send a request to Folio Producer
 */
public class CommonsHttpRequestFactoryHelper   {
	private static final RequestTranslator JSON_TRANSLATOR = new JsonTranslator(GsonProducerFactory.getInstance());
	private static final RequestTranslator QUERY_TRANSLATOR = new HttpParameterTranslator();
	
	private final HttpSigner oauthSigner;
		
	/**
	 * Instantiates a new commons http request factory helper.
	 *
	 * @param consumerKey the consumer key
	 * @param secretKey the secret key
	 */
	public CommonsHttpRequestFactoryHelper(String consumerKey, String secretKey) {
		this.oauthSigner = new OAuthSigner(consumerKey,secretKey);;
	}

	/**
	 * Gets the ticket signer.
	 *
	 * @param ticket the ticket
	 * @return the ticket signer
	 */
	public HttpSigner getTicketSigner(String ticket) {
		return new TicketSigner(ticket);
	}

	/**
	 * Gets the oauth signer.
	 *
	 * @return the oauth signer
	 */
	public HttpSigner getOauthSigner() {
		return oauthSigner;
	}

	/**
	 * Gets the json translator.
	 *
	 * @return the json translator
	 */
	public  RequestTranslator getJsonTranslator() {
		return JSON_TRANSLATOR;
	}

	/**
	 * Gets the query translator.
	 *
	 * @return the query translator
	 */
	public RequestTranslator getQueryTranslator() {
		return QUERY_TRANSLATOR;
	}
}
