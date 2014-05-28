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


/**
 * The http metadata associated with an upload preview request/response
 * see Adobe DPS documentation for more information.
 */
public class UploadPreviewRequest extends AbstractUploadRequest<Status> {
	private final UploadAssetConfig config;
	private final boolean isLandscape;

	/**
	 * For landscape.
	 *
	 * @param baseAddress the base address
	 * @param ticket the ticket
	 * @param config the config
	 * @return the upload preview request
	 */
	public static UploadPreviewRequest forLandscape(String baseAddress, String ticket, UploadAssetConfig config) {
		return new UploadPreviewRequest(baseAddress,ticket,config,true);
	}

	/**
	 * For portrait.
	 *
	 * @param baseAddress the base address
	 * @param ticket the ticket
	 * @param config the config
	 * @return the upload preview request
	 */
	public static UploadPreviewRequest forPortrait(String baseAddress, String ticket, UploadAssetConfig config) {
		return new UploadPreviewRequest(baseAddress,ticket,config,false);
	}
	
	/**
	 * For landscape.
	 *
	 * @param config the config
	 * @return the upload preview request
	 */
	public static UploadPreviewRequest forLandscape(UploadAssetConfig config) {
		return new UploadPreviewRequest(config,true);
	}

	/**
	 * For portrait.
	 *
	 * @param config the config
	 * @return the upload preview request
	 */
	public static UploadPreviewRequest forPortrait(UploadAssetConfig config) {
		return new UploadPreviewRequest(config,false);
	}
	
	private UploadPreviewRequest(UploadAssetConfig config, boolean isLandscape) {
		this.config = config;
		this.isLandscape = isLandscape;
	}
	

	private UploadPreviewRequest(String baseAddress, String ticket, UploadAssetConfig config, boolean isLandscape) {
		this.config = config;
		this.isLandscape = isLandscape;
	}

	/* (non-Javadoc)
	 * @see com.timeInc.dps.http.request.HttpRequestMapper#getConfig()
	 */
	@Override
	public UploadAssetConfig getConfig() {
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
		if(isLandscape)
			return ProducerPath.uploadLandscapePreview(config.getFolioId());
		else
			return ProducerPath.uploadPortraitPreview(config.getFolioId());
	}
}
