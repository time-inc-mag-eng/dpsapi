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
package com.timeInc.dps.publish;

import java.util.concurrent.ExecutorService;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;

import com.timeInc.dps.httpcommons.DefaultRequestFactory;
import com.timeInc.dps.httpcommons.HttpSender;
import com.timeInc.dps.httpcommons.RequestFactory;
import com.timeInc.dps.publish.httpcommons.request.PublishAbstractRequestFactory;
import com.timeInc.dps.publish.response.Result;
import com.timeInc.dps.publish.translator.GsonPublishFactory;
import com.timeInc.dps.publish.translator.PublishResponseHandler;
import com.timeInc.dps.translator.HttpParameterTranslator;
import com.timeInc.dps.translator.ResponseHandler;
import com.timeInc.dps.translator.XmlResponseTranslator;

/**
 * Factory methods for PublishClient and AsyncPublishClient by
 * using default pre-configured dependencies 
 *
 */

public class PublishClients {
	/** These classes are thread-safe **/
	private static final ResponseHandler<Result> responseHandler = new PublishResponseHandler(new XmlResponseTranslator(GsonPublishFactory.getInstance()));
	private static final DefaultRequestFactory reqFactory = new PublishAbstractRequestFactory(new HttpParameterTranslator());

	private PublishClients() {}
	
	/**
	 * @see com.timeInc.dps.publish.HttpPublishClient
	 * Constructs an http publish client that is meant to be used
	 * by a single thread 
	 * @param baseUrl the baseUrl to make publish request ex. https://origin.adobe-dcfs.com/ddp/
	 * @return a single thread PublishClient that makes requests using HTTP
	 */
	public static PublishClient getSingleThreadedClient(String baseUrl) {
		return getHttpClient(baseUrl,1);
	}
	
	
	
	/**
	 * @see com.timeInc.dps.publish.ConcurrentPublishClient
	 * Constructs an http publish client that is meant to be used
	 * in a multi-threaded environment
	 * @param baseUrl the baseUrl to make publish request ex. https://origin.adobe-dcfs.com/ddp/
	 * @param service the executor service that determines how each requests gets scheduled, executed, etc
	 * @param maxConnections maximum number of http connections to make concurrently
	 * @return a thread-safe AsyncPublishClient that makes requests using HTTP
	 */
	public static AsyncPublishClient getMultiThreadedClient(String baseUrl, ExecutorService service, int maxConnections) {
		return new ConcurrentPublishClient(getHttpClient(baseUrl,maxConnections),service);
	}

	
	private static PublishClient getHttpClient(String baseUrl, int maxConnections) {
		HttpSender<Result> sender = getSender(getHttpCommonsClient(1));
		PublishClient client = new HttpPublishClient(baseUrl,sender,new RequestFactory(reqFactory));
		return client;
	}
	

	private static HttpSender<Result> getSender(HttpClient client) {
		return HttpSender.get(client,responseHandler,Result.class);
	}


	private static HttpClient getHttpCommonsClient(int maxConnections) {
			HttpParams httpParams = new BasicHttpParams();
			httpParams.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 50000);
			httpParams.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 50000);
			httpParams.setBooleanParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, true);
			httpParams.setBooleanParameter(CoreConnectionPNames.TCP_NODELAY, true);

			ClientConnectionManager cm = new PoolingClientConnectionManager();
			((PoolingClientConnectionManager)cm).setMaxTotal(maxConnections);
			((PoolingClientConnectionManager)cm).setDefaultMaxPerRoute(maxConnections);

			HttpClient httpClient = new DefaultHttpClient(cm,httpParams);


			return httpClient;
		}
	}
