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
package com.timeInc.dps.producer.request.config;

import java.io.File;

import com.timeInc.dps.translator.TranslatableRequest;

/**
 * The upload asset request for a folio. I.e. upload html resources and uploading articles.
 *  See Adobe DPS folio documentation for more information.
 */
public class UploadAssetConfig implements TranslatableRequest {
	private final transient String folioId;
	private final transient File asset;
	
	/**
	 * Instantiates a new upload asset config.
	 *
	 * @param folioId the folio id
	 * @param asset the asset
	 */
	public UploadAssetConfig(String folioId, File asset) {
		super();
		this.folioId = folioId;
		this.asset = asset;
	}

	/**
	 * Gets the folio id.
	 *
	 * @return the folio id
	 */
	public String getFolioId() {
		return folioId;
	}

	/**
	 * Gets the asset.
	 *
	 * @return the asset
	 */
	public File getAsset() {
		return asset;
	}
}
