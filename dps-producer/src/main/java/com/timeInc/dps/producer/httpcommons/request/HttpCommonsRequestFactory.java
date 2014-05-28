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

import org.apache.http.client.methods.HttpUriRequest;

import com.timeInc.dps.http.request.HttpRequestMapper;
import com.timeInc.dps.http.request.HttpRequestMapper.Method;
import com.timeInc.dps.httpcommons.HttpDeleteRequest;
import com.timeInc.dps.httpcommons.HttpGetRequest;
import com.timeInc.dps.httpcommons.HttpMultiPartRequest;
import com.timeInc.dps.httpcommons.HttpPostRequest;
import com.timeInc.dps.httpcommons.HttpCommonsRequest;
import com.timeInc.dps.producer.http.request.ProducerRequest;
import com.timeInc.dps.producer.http.request.ProducerVisitor;
import com.timeInc.dps.producer.http.request.TicketRequest;
import com.timeInc.dps.producer.http.request.UploadRequest;
import com.timeInc.dps.producer.httpcommons.authorization.HttpSigner;
import com.timeInc.dps.translator.RequestTranslator;

/**
 * A factory class that bridges a {@link com.timeInc.dps.producer.http.request.ProducerRequest} 
 * to a {@link org.apache.http.client.methods.HttpUriRequest} so it can be sent off to
 * a {@link HttpProducerSender}
 */
public class HttpCommonsRequestFactory implements ProducerVisitor<HttpUriRequest> {
	private final CommonsHttpRequestFactoryHelper factory;

	/**
	 * Instantiates a new http commons request factory.
	 *
	 * @param factory the factory
	 */
	public HttpCommonsRequestFactory(CommonsHttpRequestFactoryHelper factory) {
		this.factory = factory;
	}
	
	/**
	 * Instantiates a new http commons request factory.
	 *
	 * @param consumerKey the consumer key
	 * @param secretKey the secret key
	 */
	public HttpCommonsRequestFactory(String consumerKey, String secretKey) {
		this(new CommonsHttpRequestFactoryHelper(consumerKey,secretKey));
	}
	
	
	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.http.request.ProducerVisitor#visit(com.timeInc.dps.producer.http.request.ProducerRequest)
	 */
	@Override
	public HttpUriRequest visit(ProducerRequest<?> request) {
		return get(request);
	}

	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.http.request.ProducerVisitor#visit(com.timeInc.dps.producer.http.request.TicketRequest)
	 */
	@Override
	public HttpUriRequest visit(TicketRequest<?> request) {
		return get(request);
	}
	
	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.http.request.ProducerVisitor#visit(com.timeInc.dps.producer.http.request.UploadRequest)
	 */
	@Override
	public HttpUriRequest visit(UploadRequest<?> request) {
		return get(request);
	}

	private HttpUriRequest get(ProducerRequest<?> request) {
		RequestTranslator translator = getTranslator(request.getMethod());
		HttpSigner signer = getHttpSigner(request);

		HttpCommonsRequest folioRequest = null;

		switch(request.getMethod()) {
			case DELETE:
				folioRequest = new HttpDeleteRequest(translator);
				break;
			case POST:				
				folioRequest = new HttpPostRequest(translator);
				break;
			case MULTI_PART: 
				if(request instanceof UploadRequest) {	
					folioRequest = new HttpMultiPartRequest(translator,((UploadRequest<?>)request).getFile());
					break;
				}
				else
					throw new IllegalStateException("MULTI_PART Method must be of type UploadRequest");
			case GET:
				folioRequest = new HttpGetRequest(translator);
				break;
			default: 
				throw new IllegalArgumentException("Invalid method type!");
		}

		if(request.getMethod() != Method.DELETE) 
			folioRequest.setParameter(request.getConfig());	

		HttpUriRequest httpCommonsRequest = folioRequest.generateRequest(request.getAbsolutePath());
		signer.sign(httpCommonsRequest);

		return httpCommonsRequest;
	}

	private HttpSigner getHttpSigner(ProducerRequest<?> request) { // cast ok since we pass in restricted parameters from the public methods
		if(request instanceof TicketRequest ) {
			TicketRequest<?> ticketRequest = (TicketRequest<?>) request;
			return factory.getTicketSigner(ticketRequest.getTicket());
		} else {
			return factory.getOauthSigner();
		}
	}

	private RequestTranslator getTranslator(HttpRequestMapper.Method method) {
		switch(method) {
			case POST:
				return factory.getJsonTranslator();
			case MULTI_PART:
				return factory.getJsonTranslator();
			default:
				return factory.getQueryTranslator();
		}
	}
}
