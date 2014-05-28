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

import java.net.SocketTimeoutException;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;

import com.timeInc.dps.http.request.HttpRequestMapper;
import com.timeInc.dps.httpcommons.HttpCommonsRequest;
import com.timeInc.dps.httpcommons.HttpSender;
import com.timeInc.dps.httpcommons.RequestFactory;
import com.timeInc.dps.publish.http.request.binding.CancelRequest;
import com.timeInc.dps.publish.http.request.binding.LoginRequest;
import com.timeInc.dps.publish.http.request.binding.PublishRequest;
import com.timeInc.dps.publish.http.request.binding.PublishStatusRequest;
import com.timeInc.dps.publish.http.request.binding.UnpublishRequest;
import com.timeInc.dps.publish.request.config.LoginConfig;
import com.timeInc.dps.publish.request.config.PublishConfig;
import com.timeInc.dps.publish.request.config.TicketConfig;
import com.timeInc.dps.publish.request.config.UnpublishConfig;
import com.timeInc.dps.publish.response.Credential;
import com.timeInc.dps.publish.response.Publish;
import com.timeInc.dps.publish.response.PublishingStatus;
import com.timeInc.dps.publish.response.Result;

public class HttpPublishClient implements PublishClient {
	private final String baseUrl;
	private final HttpSender<Result> sender;
	private final RequestFactory factory;
	
	
	/**
	 * Instantiates a new http publish client.
	 *
	 * @param baseUrl the base url
	 * @param sender the sender
	 * @param factory the factory
	 */
	public HttpPublishClient(String baseUrl, HttpSender<Result> sender, RequestFactory factory) {
		this.baseUrl = baseUrl;
		this.sender = sender;
		this.factory = factory;
		
	}

	/* (non-Javadoc)
	 * @see com.timeInc.dps.publish.PublishClient#signIn(com.timeInc.dps.publish.request.config.LoginConfig)
	 */
	@Override
	public Credential signIn(LoginConfig config) {
		LoginRequest request = new LoginRequest(baseUrl,config);
		return getResult(request);
	}

	/* (non-Javadoc)
	 * @see com.timeInc.dps.publish.PublishClient#publish(com.timeInc.dps.publish.request.config.PublishConfig)
	 */
	@Override
	public Publish publish(PublishConfig config) {
		PublishRequest request = new PublishRequest(baseUrl,config);
		return getResult(request);
	}

	/* (non-Javadoc)
	 * @see com.timeInc.dps.publish.PublishClient#getPublishStatus(com.timeInc.dps.publish.request.config.TicketConfig)
	 */
	@Override
	public PublishingStatus getPublishStatus(TicketConfig config) {
		PublishStatusRequest request = new PublishStatusRequest(baseUrl,config);
		return getResult(request);
	}

	/* (non-Javadoc)
	 * @see com.timeInc.dps.publish.PublishClient#cancelPublish(com.timeInc.dps.publish.request.config.TicketConfig)
	 */
	@Override
	public Result cancelPublish(TicketConfig config) {
		CancelRequest request = new CancelRequest(baseUrl,config);
		return getResult(request);
	}

	/* (non-Javadoc)
	 * @see com.timeInc.dps.publish.PublishClient#unpublishFolio(com.timeInc.dps.publish.request.config.UnpublishConfig)
	 */
	@Override
	public Result unpublishFolio(UnpublishConfig config) {
		UnpublishRequest request = new UnpublishRequest(baseUrl,config);
		return getResult(request);
	}
	

	private <T extends Result> T getResult(HttpRequestMapper<T> request) {
		HttpCommonsRequest httpRequest = factory.getRequestFor(request);
		Class<T> responseCls = request.getResponseClass();
		String requestPath = request.getAbsolutePath();
		
		return rethrowExceptionIfAny(responseCls,httpRequest.generateRequest(requestPath)); 
	}
	
	
	private <T extends Result> T rethrowExceptionIfAny(Class<T> response, HttpUriRequest request) {
		try {
			return sender.sendRequest(request,response);
		} catch (SocketTimeoutException e) {
			throw new PublishException(e);
		} catch (ConnectTimeoutException e) {
			throw new PublishException(e);
		}
	}
}
