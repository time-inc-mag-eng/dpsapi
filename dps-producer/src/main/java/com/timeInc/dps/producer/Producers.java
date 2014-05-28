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
package com.timeInc.dps.producer;

import org.apache.http.client.HttpClient;

import com.timeInc.dps.producer.httpcommons.request.HttpCommonsRequestFactory;
import com.timeInc.dps.producer.httpcommons.request.HttpProducerSender;
import com.timeInc.dps.producer.translator.ProducerResponseHandler;

class Producers {
	private Producers() {}
	
	/**
	 * Gets the http producer.
	 *
	 * @param client the client
	 * @param consumerKey the consumer key
	 * @param consumerSecret the consumer secret
	 * @param handler the handler
	 * @return the http producer
	 */
	public static HttpProducer getHttpProducer(HttpClient client, String consumerKey, String consumerSecret, RetryHandler handler) {
		return new RetryingHttpProducer(new HttpProducerSender(client, new ProducerResponseHandler()), new HttpCommonsRequestFactory(consumerKey,consumerSecret),handler);
	}
	
	/**
	 * Gets the http producer.
	 *
	 * @param consumerKey the consumer key
	 * @param consumerSecret the consumer secret
	 * @param handler the handler
	 * @return the http producer
	 */
	public static HttpProducer getHttpProducer(String consumerKey, String consumerSecret, RetryHandler handler) {
		HttpProducer producer =  new RetryingHttpProducer(new HttpCommonsRequestFactory(consumerKey,consumerSecret),handler);
		return producer;
	}
}
