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
package com.timeInc.dps.httpcommons;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.net.SocketTimeoutException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.timeInc.dps.exception.DpsCommunicationException;
import com.timeInc.dps.translator.ResponseHandler;


/**
 * Sends a HTTP request and returns back a response of type T
 *
 * @param <T> the response type
 */
public class HttpSender<T> {
	private static final int INTERNAL_ERROR_CODE = 500;
	
	private static final Logger log = Logger.getLogger(HttpSender.class);
	
	private final HttpClient client;
	private final ResponseHandler<T> responseHandler;
	private final Class<T> resultCls;
	
	/**
	 * Instantiates a new HttpSender
	 *
	 * @param <T> the response type
	 * @param client the HttpClient
	 * @param responseHandler the response handler
	 * @param resultCls the response class
	 * @return an instance of HttpSender
	 */
	public static <T> HttpSender<T> get(HttpClient client, ResponseHandler<T> responseHandler, Class<T> resultCls) {
		return new HttpSender<T>(client,responseHandler,resultCls);
 	}
	
	/**
	 * Instantiates a new http sender.
	 *
	 * @param client the client
	 * @param responseHandler the response handler
	 * @param resultCls the response class
	 */
	public HttpSender(HttpClient client, ResponseHandler<T> responseHandler, Class<T> resultCls) {
		this.client = client;
		this.responseHandler = responseHandler;
		this.resultCls = resultCls;
	}
	
	@SuppressWarnings("unchecked")
	protected HttpSender(HttpClient client, ResponseHandler<T> responseHandler) {
		this.client = client;
		this.responseHandler = responseHandler;
		this.resultCls = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	
	/**
	 * Send a http request and return the response of subtype T
	 *
	 * @param <V> the response type
	 * @param request the http request
	 * @param responseCls the response class
	 * @return the response of type V
	 * @throws SocketTimeoutException 
	 * @throws ConnectTimeoutException 
	 * @throws DpsCommunicationException if {@link HttpSender#INTERNAL_ERROR_CODE} is received as the status code.
	 */
	public <V extends T> V sendRequest(HttpUriRequest request, Class<V> responseCls) throws SocketTimeoutException, ConnectTimeoutException {
		try {
			HttpResponse response = getResponse(request);
			handleUnknownErrorStatusCodeIfAny(response);
			V convertedResponse = convertResponse(responseCls,getResponseAsString(response));
			return convertedResponse;
		} finally {
			if(request instanceof HttpRequestBase) {
				log.debug("Releasing connection");
				((HttpRequestBase)request).releaseConnection();
			}			
		}
	}
	
	
	/**
	 * Send a http request and receive a byte array.
	 *
	 * @param request the http request
	 * @return the byte[] response
	 * @throws SocketTimeoutException
	 * @throws ConnectTimeoutException 
	 * @throws DpsCommunicationException if {@link HttpSender#INTERNAL_ERROR_CODE} is received as the status code.
	 */
	public byte[] sendRequest(HttpUriRequest request) throws SocketTimeoutException, ConnectTimeoutException { 
		try {
			HttpResponse response = getResponse(request);
			handleUnknownErrorStatusCodeIfAny(response);
			return handleDownloadRequestError(response);	
		} finally {
			if(request instanceof HttpRequestBase) {
				log.debug("Releasing connection");
				((HttpRequestBase)request).releaseConnection();
			}
		}
	}
	
	private byte[] handleDownloadRequestError(HttpResponse response) {
		if(getHttpStatusCode(response) != 200) {
			convertResponse(resultCls, getResponseAsString(response));
			return null; // this should never return null since a successful request always returns a 200
		} else {
			try {
				return EntityUtils.toByteArray(response.getEntity());
			} catch (IOException e) {
				throw new DpsCommunicationException(e);
			} 
		}
	}
	
	private String getResponseAsString(HttpResponse response) {
		try {
			String resp = EntityUtils.toString(response.getEntity());
			
			if(resp == null || resp.isEmpty()) {
				throw new DpsCommunicationException("Response was empty");
			} else
				return resp;
			
			
		} catch (IOException e) {
			throw new DpsCommunicationException(e);
		}
	}

	private <V extends T> V convertResponse(Class<V> responseCls, String responseStr) {		
		return responseHandler.handleResponse(responseCls,responseStr);
	}

	private HttpResponse getResponse(HttpUriRequest request) throws SocketTimeoutException, ConnectTimeoutException {
		try {	
			log.debug("Sending request:" + request.toString());
			return client.execute(request);
		} catch(SocketTimeoutException ex) {
			throw ex;
		} catch(ConnectTimeoutException ct) {
			throw ct;
		} catch (ClientProtocolException e) {
			throw new DpsCommunicationException(e);
		} catch(IOException ioEx) {
			throw new DpsCommunicationException(ioEx);
		}
	}
	
	private void handleUnknownErrorStatusCodeIfAny(HttpResponse response) {
		int statusCode = getHttpStatusCode(response);
		
		try {
			handleError(statusCode);
		} catch(RuntimeException ex) {
			EntityUtils.consumeQuietly(response.getEntity());
			throw ex;
		}
	}
	
	private static int getHttpStatusCode(HttpResponse response) {
		int statusCode = response.getStatusLine().getStatusCode();
		log.debug("Got http status code:" + statusCode);
		return statusCode;
	}
	
	protected void handleError(int statusCode) {
		if(statusCode == INTERNAL_ERROR_CODE)
			throw new DpsCommunicationException("Internal server error " + INTERNAL_ERROR_CODE);
	}
}
