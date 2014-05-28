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

import org.apache.log4j.Logger;

import com.timeInc.dps.producer.exception.ServiceUnavailableException;
import com.timeInc.dps.producer.http.request.DownloadRequest;
import com.timeInc.dps.producer.http.request.ModifiableTicketRequest;
import com.timeInc.dps.producer.request.config.CloseSessionConfig;
import com.timeInc.dps.producer.request.config.OpenSessionConfig;
import com.timeInc.dps.producer.response.Status;

/**
 * Retries a request if a ServiceUnavailableException is thrown by using a different api server.
 */
public class RetryingManagedProducer implements ManagedProducer {
	private final Logger log = Logger.getLogger(RetryingManagedProducer.class);
	
	private final ManagedProducer client;
	private final int timesToRetry;


	/**
	 * Instantiates a new retrying managed producer.
	 *
	 * @param client the client
	 * @param timesToRetry the times to retry
	 */
	public RetryingManagedProducer(ManagedProducer client, int timesToRetry) {
		this.client = client;
		this.timesToRetry = timesToRetry;
	}

	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.ManagedProducer#open(com.timeInc.dps.producer.request.config.OpenSessionConfig)
	 */
	@Override
	public void open(OpenSessionConfig config) {
		client.open(config);
	}

	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.ManagedProducer#isOpen()
	 */
	@Override
	public boolean isOpen() {
		return client.isOpen();
	}

	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.ManagedProducer#close(com.timeInc.dps.producer.request.config.CloseSessionConfig)
	 */
	@Override
	public void close(CloseSessionConfig config) {
		client.close(config);
	}

	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.ManagedProducer#useNewServer()
	 */
	@Override
	public void useNewServer() {
		client.useNewServer();

	}

	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.ManagedProducer#sendRequest(com.timeInc.dps.producer.http.request.ModifiableTicketRequest)
	 */
	@Override
	public <V extends Status> V sendRequest(ModifiableTicketRequest<V> request) {
		int counter = timesToRetry;
		
		V response = null;

		while(counter > 0) {
			response = launderUnavailableException(request);
			if(response != null)
				return response;
			else
				counter--;
		}
		return client.sendRequest(request);
	}
	
	
	<V extends Status> V launderUnavailableException(ModifiableTicketRequest<V> request) {
		try {
			return client.sendRequest(request);
		} catch(ServiceUnavailableException sue) {
			log.info("Trying to use new server", sue);
			useNewServer();
		}
		
		return null;
	}
	
	
	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.ManagedProducer#retrieve(com.timeInc.dps.producer.http.request.DownloadRequest)
	 */
	@Override
	public byte[] retrieve(DownloadRequest request) {
		int counter = timesToRetry;
		
		byte[] response = null;

		while(counter > 0) {
			response = launderUnavailableException(request);
			if(response != null)
				return response;
			else
				counter--;
			
		}
		return client.retrieve(request);
	}
	
	byte[] launderUnavailableException(DownloadRequest request) {
		try {
			return client.retrieve(request);
		} catch(ServiceUnavailableException sue) {
			log.warn("Trying to use new server",sue);
			useNewServer();
		}
		
		return null;
	}
}
