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

/**
 * The Interface RetryHandler that is used to determine whether the request
 * should be retried or not. This can be useful in the context where Adobe 
 * returns spurious "Work flow errors"
 */
public interface RetryHandler {
	
	/**
	 * Determines whether the request should be retried
	 * @param retryCount the current retry count
	 * @param errorCode the error code
	 * @param errorDetail the error detail
	 * @return true if it can retry; false otherwise
	 */
	boolean canRetry(int retryCount, String errorCode, String errorDetail);
}
