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
package com.timeInc.dps.translator;

/**
 * Thrown when the response returned an error of some sort.
 */
public class ResponseHandlerException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private final String description;
	private final String errorStatus;
	
	/**
	 * Instantiates a new response handler exception.
	 *
	 * @param errorStatus the error status
	 * @param description the description
	 */
	public ResponseHandlerException(String errorStatus, String description) {
		super(errorStatus + " " + description);
		
		this.errorStatus = errorStatus;
		this.description = description;
		
	}
	
	/**
	 * Instantiates a new response handler exception.
	 *
	 * @param errorStatus the error status
	 * @param description the description
	 * @param t the cause
	 */
	public ResponseHandlerException(String errorStatus, String description, Throwable t) {
		super(errorStatus + " " + description,t);
		
		this.errorStatus = errorStatus;
		this.description = description;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return "Error Status:" + errorStatus + " Description:" + description;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the error status.
	 *
	 * @return the error status
	 */
	public String getErrorStatus() {
		return errorStatus;
	}
}
