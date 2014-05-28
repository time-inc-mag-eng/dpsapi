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

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;

import com.timeInc.dps.publish.request.config.LoginConfig;
import com.timeInc.dps.publish.request.config.PublishConfig;
import com.timeInc.dps.publish.request.config.TicketConfig;
import com.timeInc.dps.publish.request.config.UnpublishConfig;
import com.timeInc.dps.publish.response.Credential;
import com.timeInc.dps.publish.response.Publish;
import com.timeInc.dps.publish.response.PublishingStatus;
import com.timeInc.dps.publish.response.Result;



public class ConcurrentPublishClient implements AsyncPublishClient {
	private static final Logger log = Logger.getLogger(ConcurrentPublishClient.class);
	private final PublishClient wrappedClient;
	private final ExecutorService executor; 
	
	
	// wrappedClient needs to be thread-safe
	public ConcurrentPublishClient(PublishClient wrappedClient, ExecutorService executor) {
		this.executor = executor;
		this.wrappedClient = wrappedClient;
	}
	
	@Override
	public Future<Credential> signIn(final LoginConfig config) {
		log.debug("Submitting sign in request with config: " + config);
		
		return executor.submit(new Callable<Credential>() {
			@Override
			public Credential call() throws Exception {
				return wrappedClient.signIn(config);
			}
		});
	}

	@Override
	public Future<Publish> publish(final PublishConfig config) {
		log.debug("Submitting publish request with config: " + config);
		
		
		return executor.submit(new Callable<Publish>() {
			@Override
			public Publish call() throws Exception {
				return wrappedClient.publish(config);
			}
		});
	}

	@Override
	public Future<PublishingStatus> getPublishStatus(final TicketConfig config) {
		log.debug("Submitting get publish status request with config: " + config);
		
		return executor.submit(new Callable<PublishingStatus>() {
			@Override
			public PublishingStatus call() throws Exception {
				return wrappedClient.getPublishStatus(config);
			}
		});
	}

	@Override
	public Future<Result> cancelPublish(final TicketConfig config) {
		log.debug("Submitting cancel publish request with config: " + config);
		
		return executor.submit(new Callable<Result>() {
			@Override
			public Result call() throws Exception {
				return wrappedClient.getPublishStatus(config);
			}
		});
	}

	@Override
	public Future<Result> unpublishFolio(final UnpublishConfig config) {
		log.debug("Submitting unpublish folio request with config: " + config);
		
		return executor.submit(new Callable<Result>() {
			@Override
			public Result call() throws Exception {
				return wrappedClient.unpublishFolio(config);
			}
		});
	}

}
