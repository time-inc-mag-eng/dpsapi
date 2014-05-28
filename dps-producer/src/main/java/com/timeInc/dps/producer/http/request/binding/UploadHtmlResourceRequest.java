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

import java.io.File;

import com.timeInc.dps.producer.request.config.UploadAssetConfig;
import com.timeInc.dps.producer.response.Status;
import com.timeInc.dps.translator.TranslatableRequest;

/**
 * The http metadata associated with an upload html resource request/response
 * see Adobe DPS documentation for more information.
 */
public class UploadHtmlResourceRequest extends AbstractUploadRequest<Status> {
	private final UploadAssetConfig config;
	
	/**
	 * Instantiates a new upload html resource request.
	 *
	 * @param config the config
	 */
	public UploadHtmlResourceRequest(UploadAssetConfig config) {
		this.config = config;
	}
	
	/* (non-Javadoc)
	 * @see com.timeInc.dps.http.request.HttpRequestMapper#getConfig()
	 */
	@Override
	public TranslatableRequest getConfig() {
		return config;
	}

	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.http.request.UploadRequest#getFile()
	 */
	@Override
	public File getFile() {
		return config.getAsset();
	}

	@Override
	protected ProducerPath getFolioPath() {
		return ProducerPath.uploadHtmlResource(config.getFolioId());
	}
}
