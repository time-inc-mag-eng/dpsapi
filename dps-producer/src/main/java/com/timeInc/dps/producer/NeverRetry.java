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
 * Never retries a request
 */
public class NeverRetry implements RetryHandler{

	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.RetryHandler#canRetry(int, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean canRetry(int retryCount, String errorCode, String errorDetail) {
		return false;
	}

}
