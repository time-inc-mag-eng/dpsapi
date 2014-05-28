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

import com.timeInc.dps.producer.exception.ManagedProducerException;
import com.timeInc.dps.producer.http.request.DownloadRequest;
import com.timeInc.dps.producer.http.request.ModifiableTicketRequest;
import com.timeInc.dps.producer.http.request.binding.CloseSessionRequest;
import com.timeInc.dps.producer.http.request.binding.NewServerRequest;
import com.timeInc.dps.producer.http.request.binding.OpenSessionRequest;
import com.timeInc.dps.producer.request.config.CloseSessionConfig;
import com.timeInc.dps.producer.request.config.OpenSessionConfig;
import com.timeInc.dps.producer.response.OpenSession;
import com.timeInc.dps.producer.response.Server;
import com.timeInc.dps.producer.response.Status;


/**
 * Manages the auth ticket to be sent with each request. 
 */
public class ManagedServerTicket implements ManagedProducer {
	
	private final HttpProducer producer;
	private final String apiAddress;

	private boolean isOpen;

	private String downloadTicket;
	private String ticket;

	private String downloadServer;
	private String server;


	/**
	 * Instantiates a new managed server ticket.
	 *
	 * @param producer the producer
	 * @param apiAddress the api address
	 */
	public ManagedServerTicket(HttpProducer producer, String apiAddress) {
		this.producer = producer;
		this.apiAddress = apiAddress;
	}

	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.ManagedProducer#open(com.timeInc.dps.producer.request.config.OpenSessionConfig)
	 */
	@Override
	public void open(OpenSessionConfig config) {
		OpenSessionRequest openRequest = new OpenSessionRequest(apiAddress,config); 
		OpenSession openInfo = producer.sendRequest(openRequest);
		updateTickets(openInfo);
		updateServers(openInfo);
		
		setOpen(true);
	}
	
	
	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.ManagedProducer#useNewServer()
	 */
	@Override
	public void useNewServer() {
		checkIfOpen("Can not send request if session was not open");

		NewServerRequest request = new NewServerRequest(apiAddress);
		request.setTicket(ticket);
		
		Server serverInfo = producer.sendRequest(request);
		
		updateServers(serverInfo);
		updateTickets(serverInfo);
	}

	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.ManagedProducer#sendRequest(com.timeInc.dps.producer.http.request.ModifiableTicketRequest)
	 */
	@Override
	public <V extends Status> V sendRequest(ModifiableTicketRequest<V> request) {
		checkIfOpen("Can not send request if session was not open");

		updateInfoForRequest(request,false);
		
		V info = producer.sendRequest(request);
		updateTickets(info);
		
		return info;
	}
	
	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.ManagedProducer#retrieve(com.timeInc.dps.producer.http.request.DownloadRequest)
	 */
	@Override
	public byte[] retrieve(DownloadRequest request) {
		checkIfOpen("Can not download asset if session was not open");

		updateInfoForRequest(request.getWrappedRequest(),true);
		
		return producer.retrieve(request);
	}
	

	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.ManagedProducer#close(com.timeInc.dps.producer.request.config.CloseSessionConfig)
	 */
	@Override
	public void close(CloseSessionConfig config) {
		checkIfOpen("Can not close session if session was not open!");
		
		CloseSessionRequest closeRequest = new CloseSessionRequest(server,ticket,config);
		
		producer.sendRequest(closeRequest);
		setOpen(false);
	}
	
	
	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.ManagedProducer#isOpen()
	 */
	@Override
	public boolean isOpen() {
		return isOpen;
	}
	
	private void updateInfoForRequest(ModifiableTicketRequest<?> request, boolean isDownload) {
		if(!isDownload) {
			request.setTicket(ticket);
			request.setBasePath(server);
		} else {
			request.setBasePath(downloadServer);
			request.setTicket(downloadTicket);
		}
	}
	
	private void updateServers(Server info) {
		if(info.getDownloadServer() != null) 
			downloadServer = info.getDownloadServer();
		if(info.getServer() != null)
			server = info.getServer();
	}

	private void updateTickets(Status info) {
		if(info.getTicket() != null)
			ticket = info.getTicket();
		
		if(info.getDownloadTicket() != null)
			downloadTicket = info.getDownloadTicket();
	}

	private void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	private void checkIfOpen(String errorMsg) {
		if(!isOpen()) 
			throw new ManagedProducerException(errorMsg);
	}
}
