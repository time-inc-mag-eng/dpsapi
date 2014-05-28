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
package com.timeInc.dps.producer.response;

/**
 * The update folio response. See Adobe DPS folio documentation for more information.
 */
public class UpdateFolio extends Status {
	private final FolioInfo folioInfo;
	
	/**
	 * Instantiates a new update folio.
	 *
	 * @param status the status
	 * @param info the info
	 */
	public UpdateFolio(String status, FolioInfo info) {
		super(status);
		this.folioInfo = info;
	}

	/**
	 * Gets the folio info.
	 *
	 * @return the folio info
	 */
	public FolioInfo getFolioInfo() {
		return folioInfo;
	}
}
