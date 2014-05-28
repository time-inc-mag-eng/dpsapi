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
package com.timeInc.dps.exception;

/**
 * Thrown to indicate that there was trouble talking to the Dps
 * api
 */
public class DpsCommunicationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new dps communication exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public DpsCommunicationException(String message,Throwable cause) {
		super(message,cause);
	}
	
	/**
	 * Instantiates a new dps communication exception.
	 *
	 * @param cause the cause
	 */
	public DpsCommunicationException(Throwable cause) {
		super(cause);
	}


	/**
	 * Instantiates a new dps communication exception.
	 *
	 * @param message the message
	 */
	public DpsCommunicationException(String message) {
		super(message);
	}
}
