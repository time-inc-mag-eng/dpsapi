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
import java.io.IOException;
import java.net.SocketTimeoutException;

import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;
import org.apache.log4j.Logger;

import com.timeInc.dps.httpcommons.HttpSender;
import com.timeInc.dps.producer.exception.ServiceUnavailableException;
import com.timeInc.dps.producer.response.Status;
import com.timeInc.dps.translator.ResponseHandler;



/**
 * Responsible for sending requests to Folio producer
 * Converts the response received of type 
 * {@link com.timeInc.dps.producer.response.Status} by delegating to 
 *  {@link com.timeInc.dps.translator.ResponseHandler}
 * 
 */
public class HttpProducerSender extends HttpSender<Status> {
	private static final int SERVICE_UNAVAILABLE_CODE = 503;
	
	private static final Logger log = Logger.getLogger(HttpProducerSender.class);
	
	/**
	 * Instantiates a new http producer sender.
	 *
	 * @param client the http client
	 * @param stringHandler the string handler
	 */
	public HttpProducerSender(HttpClient client,
			ResponseHandler<Status> stringHandler) {
		super(client, stringHandler);
	}
	
	/**
	 * Instantiates a new http producer sender.
	 *
	 * @param stringHandler the string handler
	 */
	public HttpProducerSender(ResponseHandler<Status> stringHandler) {
		this(getDefaultHttpClient(), stringHandler);
	}
	
	
	private static HttpClient getDefaultHttpClient() {
		DefaultHttpClient client = new DefaultHttpClient();
		
		client.setHttpRequestRetryHandler(new HttpRequestRetryHandler() {
		    @Override
		    public boolean retryRequest(IOException exception, int executionCount, 
		                                HttpContext context) {
		        if (executionCount > 3) {
		        	log.warn("Maximum tries reached for client http pool ");
		                return false;
		        }
		        if (exception instanceof org.apache.http.NoHttpResponseException) {
		            log.warn("No response from server on " + executionCount + " call");
		            return true;
		        }
		        
		        return false;
		      }
		   }); 
		
		return client;
	}
	
	
	
	/**
	 * (non-Javadoc)
	 * @see com.timeInc.dps.httpcommons.HttpSender#sendRequest(org.apache.http.client.methods.HttpUriRequest, java.lang.Class)
	 * @throws ServiceUnavailableException if the status code returned is {@link HttpProducerSender#SERVICE_UNAVAILABLE_CODE}
	 */
	@Override
	public <V extends Status> V sendRequest(HttpUriRequest request, Class<V> responseCls) {
		try {
			return super.sendRequest(request, responseCls);
		} catch(SocketTimeoutException ste) {
			throw new ServiceUnavailableException(ste);
		} catch(ConnectTimeoutException cte) {
			throw new ServiceUnavailableException(cte);
		}
	}
	
	/** 
	 * (non-Javadoc)
	 * @see com.timeInc.dps.httpcommons.HttpSender#sendRequest(org.apache.http.client.methods.HttpUriRequest)
	 * @throws ServiceUnavailableException if the status code returned is {@link HttpProducerSender#SERVICE_UNAVAILABLE_CODE}
	 */
	@Override
	public byte[] sendRequest(HttpUriRequest request) { 
		try {
			return super.sendRequest(request);
		} catch(SocketTimeoutException ste) {
			throw new ServiceUnavailableException(ste);
		} catch(ConnectTimeoutException cte) {
			throw new ServiceUnavailableException(cte);
		}
	}
	
	
	@Override
	protected void handleError(int statusCode) {
		super.handleError(statusCode);
		
		if(statusCode == SERVICE_UNAVAILABLE_CODE) 
			throw new ServiceUnavailableException("Http status code return was " + SERVICE_UNAVAILABLE_CODE);
	}


}
