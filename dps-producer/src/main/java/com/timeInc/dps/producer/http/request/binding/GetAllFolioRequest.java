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
package com.timeInc.dps.producer.http.request.binding;

import com.timeInc.dps.producer.response.GetFolios;
import com.timeInc.dps.translator.TranslatableRequest;

/**
 * The http metadata associated with a get all folio request/response
 * see Adobe DPS documentation for more information.
 */
public class GetAllFolioRequest extends AbstractModifiableTicketRequest<GetFolios> {

	/* (non-Javadoc)
	 * @see com.timeInc.dps.http.request.HttpRequestMapper#getMethod()
	 */
	@Override
	public com.timeInc.dps.http.request.HttpRequestMapper.Method getMethod() {
		return Method.GET;
	}

	/* (non-Javadoc)
	 * @see com.timeInc.dps.http.request.HttpRequestMapper#getConfig()
	 */
	@Override
	public TranslatableRequest getConfig() {
		return null;
	}

	@Override
	protected ProducerPath getFolioPath() {
		return ProducerPath.foliosInfo();
	}
}
