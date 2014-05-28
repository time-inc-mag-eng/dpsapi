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

import java.util.concurrent.Future;

import com.timeInc.dps.publish.request.config.LoginConfig;
import com.timeInc.dps.publish.request.config.PublishConfig;
import com.timeInc.dps.publish.request.config.TicketConfig;
import com.timeInc.dps.publish.request.config.UnpublishConfig;
import com.timeInc.dps.publish.response.Credential;
import com.timeInc.dps.publish.response.Publish;
import com.timeInc.dps.publish.response.PublishingStatus;
import com.timeInc.dps.publish.response.Result;

/**
 * Asynchronous version of PublishClient
 * 
 * @throws PublishException if there was a problem making a request to the server
 * @throws ResponseHandlerException if an error other than SUCCESS is returned
 * 
 * @see PublishClient
 * 
 *
 */

public interface AsyncPublishClient {
	/**
	 * @see PublishClient.signIn(LoginConfig)
	 * @param config
	 * @return
	 */
	Future<Credential> signIn(LoginConfig config);
	
	/**
	 * @see PublishClient.publish(PublishConfig)
	 * @param config
	 * @return
	 */
	Future<Publish> publish(PublishConfig config);
	
	/**
	 * @see PublishClient.getPublishStatus(TicketConfig)
	 * @param config
	 * @return
	 */
	Future<PublishingStatus> getPublishStatus(TicketConfig config);
	
	/**
	 * @see PublishClient.cancelPublish(TicketConfig)
	 * @param config
	 * @return
	 */
	Future<Result> cancelPublish(TicketConfig config);
	
	/**
	 * @see PublishClient.unpublishFolio(UnpublishConfig)
	 * @param config
	 * @return
	 */
	Future<Result> unpublishFolio(UnpublishConfig config);
}
