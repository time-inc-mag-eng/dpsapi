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

import com.timeInc.dps.publish.request.config.LoginConfig;
import com.timeInc.dps.publish.request.config.PublishConfig;
import com.timeInc.dps.publish.request.config.TicketConfig;
import com.timeInc.dps.publish.request.config.UnpublishConfig;
import com.timeInc.dps.publish.response.Credential;
import com.timeInc.dps.publish.response.Publish;
import com.timeInc.dps.publish.response.PublishingStatus;
import com.timeInc.dps.publish.response.Result;

/**
 * Client that publishes content to Adobe's distribution server.
 * 
 * @throws PublishException if there was a problem making a request to the server
 * @throws ResponseHandlerException if an error other than SUCCESS is returned
 * 
 *
 */

public interface PublishClient {
	
	/**
	 * Signs the user in to the Distribution Server using their Adobe ID and password.
	 * @param config {@link LoginConfig}
	 * @return {@link Credential}
	 */
	Credential signIn(LoginConfig config);
	
	
	
	/**
	 * Publish or update the specified folio from the folio producer workflow into the distribution service.
	 * @param config {@link PublishConfig}
	 * @return {@link Publish}
	 */
	Publish publish(PublishConfig config);
	
	
	
	/**
	 * Gets the status of a publishing request.
	 * @param config {@link TicketConfig}
	 * @return {@link PublishingStatus}
	 */
	PublishingStatus getPublishStatus(TicketConfig config);
	
	/**
	 * Cancel background publishing request for the provided request id.
	 * @param config {@link TicketConfig}
	 * @return {@link Result}
	 */
	Result cancelPublish(TicketConfig config);
	
	/**
	 * Removes the specified folio from the distribution service. Content in the folio producer service remains in place.
	 * @param config {@link UnpublishConfig}
	 * @return {@link Result}
	 */
	Result unpublishFolio(UnpublishConfig config);
}
