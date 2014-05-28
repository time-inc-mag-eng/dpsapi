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

import com.timeInc.dps.http.request.AbstractHttpRequest;
import com.timeInc.dps.producer.http.request.ProducerRequest;
import com.timeInc.dps.producer.http.request.ProducerVisitor;
import com.timeInc.dps.producer.response.Status;

/**
 * Represents a producer request. Uses {@link AbstractProducerRequest#SERVICE_PATH as the service path,
 * and requires subclasses to return a {@link ProducerPath}
 *
 * @see com.timeInc.dps.http.request.AbstractHttpRequest
 *
 * @param <T> the response of subtype {@link com.timeInc.dps.producer.response.Status}
 */
public abstract class AbstractProducerRequest<T extends Status> extends AbstractHttpRequest<T> implements ProducerRequest<T> {
	
	/** The Constant SERVICE_PATH. */
	public static final String SERVICE_PATH = "webservices";
	
	protected AbstractProducerRequest(String baseAddress) {
		super(baseAddress, SERVICE_PATH);
	}
	
	protected AbstractProducerRequest() {
		super(SERVICE_PATH);
	}
	
	protected String getRelativePath() {
		return getFolioPath().getPath();
	}
	
	protected abstract ProducerPath getFolioPath();
	
	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.http.request.ProducerRequest#accept(com.timeInc.dps.producer.http.request.ProducerVisitor)
	 */
	@Override
	public <V> V accept(ProducerVisitor<V> visitor) {
		return visitor.visit((ProducerRequest<?>)this);
	}
}
