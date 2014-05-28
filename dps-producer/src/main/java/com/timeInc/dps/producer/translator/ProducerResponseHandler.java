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
package com.timeInc.dps.producer.translator;

import org.apache.log4j.Logger;

import com.timeInc.dps.producer.response.Status;
import com.timeInc.dps.translator.JsonTranslator;
import com.timeInc.dps.translator.ResponseHandler;
import com.timeInc.dps.translator.ResponseHandlerException;
import com.timeInc.dps.translator.ResponseTranslator;

/**
 * Converts a DPS Producer response to a an object of 
 * type {@link com.timeInc.dps.producer.response.Status} failing if 
 * the status returned a result other than {@link com.timeInc.dps.producer.response.Status#SUCCESS}
 */
public class ProducerResponseHandler implements ResponseHandler<Status> {
	
	private static final Logger log = Logger.getLogger(ProducerResponseHandler.class);
	
	private final ResponseTranslator translator;
	
	/**
	 * Instantiates a new producer response handler.
	 */
	public ProducerResponseHandler() {
		this(new JsonTranslator(GsonProducerFactory.getInstance()));
	}
	
	/**
	 * Instantiates a new producer response handler.
	 *
	 * @param translator the translator to use
	 */
	public ProducerResponseHandler(ResponseTranslator translator) {
		this.translator = translator;
	}

	/**
	 * @see com.timeInc.dps.translator.ResponseHandler#handleResponse(java.lang.Class, java.lang.String)
	 * @throws ResponseHandlerException if the status in the response does not match {@link com.timeInc.dps.producer.response.Status#SUCCESS}
	 */
	@Override
	public <T extends Status> T handleResponse(Class<T> responseCls, String msg) {
		log.debug("Deserializing: " + msg);
		
		T deserializedObj = null;
		
		try {
			deserializedObj =  translator.convertToObject(responseCls, msg);
		} catch(RuntimeException ex) {
			log.error("Error deserializing msg:" + msg);
			throw ex;
		}
		
		handleStatusError(deserializedObj);
		return deserializedObj;
	}	
	
	private void handleStatusError(Status info) {
		String status = info.getStatus();
		
		if(!status.equalsIgnoreCase(Status.SUCCESS)) {
			throw new ResponseHandlerException(status,info.getErrorDetail());
		}
	}
}
