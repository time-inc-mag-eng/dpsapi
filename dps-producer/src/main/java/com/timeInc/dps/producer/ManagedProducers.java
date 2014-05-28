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

/**
 * Factory for {@link ManagedProducer}
 */
public class ManagedProducers {
	private ManagedProducers() {}
	
	/**
	 * Gets an http based ManagedProducer that never retries if the server is unavailable. 
	 *
	 * @param apiAddress the api address
	 * @param consumerKey the consumer key
	 * @param consumerSecret the consumer secret
	 * @return the ManagedProducer
	 */
	public static ManagedProducer getHttpClient(String apiAddress, String consumerKey, String consumerSecret) {
		return getHttpClient(apiAddress,consumerKey,consumerSecret, new NeverRetry());
	}
	
	/**
	 * Gets an http based ManagedProducer that retries if the server is unavailable.
	 * @param apiAddress the api address
	 * @param consumerKey the consumer key
	 * @param consumerSecret the consumer secret
	 * @param timesToRetry the times to try a request using the new server before giving up
	 * @return the ManagedProducer
	 */
	public static ManagedProducer getHttpRetryingClient(String apiAddress, String consumerKey, String secretKey, int timesToRetry) {
		return getHttpRetryingClient(apiAddress, consumerKey, secretKey, timesToRetry, new NeverRetry());
	}
	
	/**
	 * Gets an http based ManagedProducer that retries a request based on the RetryHandler if the response 
	 * returned an error of some sort.
	 *
	 * @param apiAddress the api address
	 * @param consumerKey the consumer key
	 * @param consumerSecret the consumer secret
	 * @param handler the handler to use that determines whether the request should be retried
	 * @return the ManagedProducer
	 */
	public static ManagedProducer getHttpClient(String apiAddress, String consumerKey, String consumerSecret, RetryHandler handler) {
		return new ManagedServerTicket(Producers.getHttpProducer(consumerKey,consumerSecret,handler), apiAddress);	
	}
	
	/**
	 * Gets an http based ManagedProducer that retries a request based on the RetryHandler if the response 
	 * returned an error of some sort and uses a new api server if the previous request was sent to an unavailable
	 * server.*
	 *
	 * @param apiAddress the api address
	 * @param consumerKey the consumer key
	 * @param consumerSecret the consumer secret
	 * @param timesToRetry the times to try a request using the new server before giving up
	 * @param handler the handler to use that determines whether the request should be retried
	 * @return the ManagedProducer
	 */
	public static ManagedProducer getHttpRetryingClient(String apiAddress, String consumerKey, String secretKey, int timesToRetry, RetryHandler handler) {
		return new RetryingManagedProducer(getHttpClient(apiAddress,consumerKey,secretKey,handler),timesToRetry);
	}
	
	/**
	 * Gets an http based ManagedProducer that retries a request based on the RetryHandler if the response 
	 * returned an error of some sort and uses a new api server if the previous request was sent to an unavailable
	 * server.*
	 *
	 * @param apiAddress the api address
	 * @param consumerKey the consumer key
	 * @param client the client to use for making http reqeusts
	 * @param consumerSecret the consumer secret
	 * @param timesToRetry the times to try a request using the new server before giving up
	 * @param handler the handler to use that determines whether the request should be retried
	 * @return the ManagedProducer
	 */
	public static ManagedProducer getHttpRetryingClient(HttpClient client, String apiAddress, String consumerKey, String secretKey, int timesToRetry, RetryHandler handler) {
		return new RetryingManagedProducer(new ManagedServerTicket(Producers.getHttpProducer(client, consumerKey, secretKey, handler), apiAddress), timesToRetry);
	}
}
