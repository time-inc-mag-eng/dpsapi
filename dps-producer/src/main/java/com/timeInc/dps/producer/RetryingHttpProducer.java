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

import org.apache.http.client.methods.HttpUriRequest;

import com.timeInc.dps.producer.http.request.DownloadRequest;
import com.timeInc.dps.producer.http.request.ProducerRequest;
import com.timeInc.dps.producer.http.request.ProducerVisitor;
import com.timeInc.dps.producer.httpcommons.request.HttpProducerSender;
import com.timeInc.dps.producer.response.Status;
import com.timeInc.dps.producer.translator.ProducerResponseHandler;
import com.timeInc.dps.translator.ResponseHandlerException;

/**
 * Retries a request based on the {@link RetryHandler} 
 *
 */
class RetryingHttpProducer implements HttpProducer {
	private final ProducerVisitor<HttpUriRequest> visitor;
	private final RetryHandler retryHandler;
	private final HttpProducerSender sender;
	
		
	/**
	 * Instantiates a new retrying http producer.
	 *
	 * @param sender the sender to decorate
	 * @param visitor the visitor
	 * @param retryHandler the retry handler
	 */
	public RetryingHttpProducer(HttpProducerSender sender, ProducerVisitor<HttpUriRequest> visitor, 
			RetryHandler retryHandler) {
		this.visitor = visitor; 
		this.retryHandler = retryHandler;
		this.sender = sender;
	}

	/**
	 * Instantiates a new retrying http producer.
	 *
	 * @param visitor the visitor
	 * @param retryHandler the retry handler
	 */
	public RetryingHttpProducer(ProducerVisitor<HttpUriRequest> visitor, RetryHandler retryHandler) {
		this(new HttpProducerSender(new ProducerResponseHandler()),visitor,retryHandler);
	}
	
	/**
	 * (non-Javadoc)
	 * @see com.timeInc.dps.producer.HttpProducer#sendRequest(com.timeInc.dps.producer.http.request.ProducerRequest)
	 * @throws ServiceUnavailableException
	 */
	@Override
	public <V extends Status> V sendRequest(ProducerRequest<V> request)  {
		HttpUriRequest httpRequest = request.accept(visitor);
		return retryWhenErrorMsgReceived(httpRequest,request);
	}
	
	private <V extends Status> V retryWhenErrorMsgReceived(HttpUriRequest commonsRequest, ProducerRequest<V> request) {
		int retryCount = 0;

		for(;;) {
			try {
				retryCount++;
				V convertedResponse = sender.sendRequest(commonsRequest,request.getResponseClass());
				return convertedResponse;
			} catch(ResponseHandlerException rhe) {
				if(!retryHandler.canRetry(retryCount, rhe.getErrorStatus(), rhe.getDescription()))
					throw rhe;
			}
		}
	}

	/**
	 *  (non-Javadoc)
	 * @see com.timeInc.dps.producer.HttpProducer#retrieve(com.timeInc.dps.producer.http.request.DownloadRequest)
	 * @throws ServiceUnavailableException
	 */
	@Override
	public byte[] retrieve(DownloadRequest request)   {
		HttpUriRequest httpRequest = request.getWrappedRequest().accept(visitor);
		return sender.sendRequest(httpRequest);
	}
}
