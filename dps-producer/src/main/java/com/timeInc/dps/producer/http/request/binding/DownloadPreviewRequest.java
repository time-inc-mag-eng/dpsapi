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

import com.timeInc.dps.producer.http.request.DownloadRequest;
import com.timeInc.dps.producer.http.request.ModifiableTicketRequest;
import com.timeInc.dps.producer.request.config.DownloadPreviewConfig;
import com.timeInc.dps.producer.response.Status;

/**
 * The http metadata associated with a download preview request/response.
 * see Adobe DPS documentation for more information.
 */
public class DownloadPreviewRequest implements DownloadRequest {

	private final WrappedDownloadPreviewRequest request;


	/**
	 * Gets the portrait preview for.
	 *
	 * @param baseAddress the base address
	 * @param ticket the ticket
	 * @param config the config
	 * @return the portrait preview for
	 */
	public static DownloadPreviewRequest getPortraitPreviewFor(String baseAddress, String ticket, DownloadPreviewConfig config) {
		return new DownloadPreviewRequest(baseAddress,ticket,config,false);
	}

	/**
	 * Gets the landscape preview for.
	 *
	 * @param baseAddress the base address
	 * @param ticket the ticket
	 * @param config the config
	 * @return the landscape preview for
	 */
	public static DownloadPreviewRequest getLandscapePreviewFor(String baseAddress, String ticket, DownloadPreviewConfig config) {
		return new DownloadPreviewRequest(baseAddress,ticket,config,true);
	}

	private DownloadPreviewRequest(String baseAddress, String ticket, DownloadPreviewConfig config, boolean isLandscape) {
		this.request = new WrappedDownloadPreviewRequest(baseAddress,ticket,config,isLandscape);
	}

	/**
	 * Instantiates a new download preview request.
	 *
	 * @param config the config
	 * @param isLandscape the is landscape
	 */
	public DownloadPreviewRequest(DownloadPreviewConfig config, boolean isLandscape) {
		this.request = new WrappedDownloadPreviewRequest(config,isLandscape);
	}
	
	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.http.request.DownloadRequest#getWrappedRequest()
	 */
	@Override
	public ModifiableTicketRequest<Status> getWrappedRequest() {
		return request;
	}

	private class WrappedDownloadPreviewRequest extends AbstractModifiableTicketRequest<Status> {
		private final DownloadPreviewConfig config;
		private final boolean isLandscape;

		private WrappedDownloadPreviewRequest(String baseAddress, String ticket, DownloadPreviewConfig config, boolean isLandscape) {
			super(baseAddress,ticket);
			this.config = config;
			this.isLandscape = isLandscape;
		}


		private WrappedDownloadPreviewRequest(DownloadPreviewConfig config, boolean isLandscape) {
			this.config = config;
			this.isLandscape = isLandscape;
		}


		@Override
		public DownloadPreviewConfig getConfig() {
			return config;
		}

		@Override
		public Method getMethod() {
			return Method.GET;
		}

		@Override
		protected ProducerPath getFolioPath() {
			if(isLandscape)
				return ProducerPath.downloadLandscapePreview(config.getFolioId());
			else
				return ProducerPath.downloadPortraitPreview(config.getFolioId());	
		}	
	}
}
