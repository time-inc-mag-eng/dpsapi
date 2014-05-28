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

import com.timeInc.dps.producer.http.request.DownloadRequest;
import com.timeInc.dps.producer.http.request.ModifiableTicketRequest;
import com.timeInc.dps.producer.request.config.CloseSessionConfig;
import com.timeInc.dps.producer.request.config.OpenSessionConfig;
import com.timeInc.dps.producer.response.Status;

/**
 * A managed producer that knows how to handle the process of sending a request from start to finish.
 * It handles opening and terminating a session, using new api servers if provided, and
 * using new auth tickets for subsequent requests. 
 * 
 * 
 */
public interface ManagedProducer {
	
	/**
	 * Opens a session to prepare for requests to be sent
	 * @param config
	 */
	void open(OpenSessionConfig config);
	
	/**
	 * Closes the current session
	 * @param config
	 */
	void close(CloseSessionConfig config);
	
	/**
	 * Determines whether there is an open session
	 * @return true if there is otherwise false
	 */
	boolean isOpen();

	/**
	 * Use a new server to make api requests to
	 */
	void useNewServer();
	
	/**
	 * @see HttpProducer
	 * @param request
	 * @return
	 */
	<V extends Status> V sendRequest(ModifiableTicketRequest<V> request);
	
	/**
	 * @see HttpProducer
	 * @param request
	 * @return
	 */
	byte[] retrieve(DownloadRequest request);
}
