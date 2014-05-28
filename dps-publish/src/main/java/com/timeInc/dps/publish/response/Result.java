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
package com.timeInc.dps.publish.response;

import com.timeInc.dps.translator.TranslatableResponse;

/**
 * A Result represents a holder for response status.
 * 
 * It is common to all responses.
 *
 * A successful status is represented with {@link com.timeInc.dps.publish.response.Result.Success}
 * 
 */

public class Result implements TranslatableResponse {
	
	/** The Constant SUCCESS. */
	public static final String SUCCESS = "SUCCESS";
	
	private final String status;
	private final String message;
	
	
	/**
	 *  
	 * Constructs a successful response.
	 */
	public Result() {
		this(SUCCESS,"success");
	}
	
	/**
	 *  
	 * Constructs a result represented by a status and message.
	 *
	 * @param result the status
	 * @param message descriptive detail about the status
	 */
	public Result(String result, String message) {
		super();
		this.status = result;
		this.message = message;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}
