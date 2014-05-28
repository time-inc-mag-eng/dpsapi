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
package com.timeInc.dps.producer.request.model;

import java.util.Date;
import static org.junit.Assert.*;
import org.junit.Test;

import com.timeInc.dps.producer.enums.*;
import com.timeInc.dps.producer.request.config.CreateFolioConfig;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class CreateFolioConfigTest {	
	/** Required **/
	private static final String FOLIO_NAME = "folioName";
	private static final String MAGAZINE_TITLE = "magazineTitle";
	private static final String FOLIO_NUMBER = "folioNumber";
	private static final int RESOLUTION_WIDTH = 1024;
	private static final int RESOLUTION_HEIGHT = 768;
	
	/** Optional **/
	private static final String FOLIO_DESCRIPTION = "folioDescription";
	private static final Date PUBLICATION_DATE = new Date();
	private static final Date COVER_DATE = new Date();
	private static boolean BINDING_RIGHT = false;
	private static boolean LOCKED = false;
	

	private static final AssetFormat DEFAULT_ASSET_FORMAT = AssetFormat.Auto;
	private static final JPEGQuality DEFAULT_JPG_QUALITY = JPEGQuality.High;
	private static final FolioIntent FOLIO_INENT = FolioIntent.Both;

	@Test
	public void constructRequiredProperties() {
		CreateFolioConfig.Builder builder = new CreateFolioConfig.Builder(FOLIO_NAME,MAGAZINE_TITLE,FOLIO_NUMBER,RESOLUTION_WIDTH,RESOLUTION_HEIGHT);
		
		CreateFolioConfig actualFolioRequest = builder.build();
		
		assertThat("Folio Name:",actualFolioRequest.getFolioName(),equalTo(FOLIO_NAME));
		
		assertThat("Magazine Title:",actualFolioRequest.getMagazineTitle(),equalTo(MAGAZINE_TITLE));
		
		assertThat("Folio Number:",actualFolioRequest.getFolioNumber(),equalTo(FOLIO_NUMBER));		
		
		assertThat("Resolution Width:",actualFolioRequest.getResolutionWidth(),equalTo(RESOLUTION_WIDTH));	
		
		assertThat("Resolution Height:",actualFolioRequest.getResolutionHeight(),equalTo(RESOLUTION_HEIGHT));	
	}
	
	
	@Test 
	public void constructOptionalField() {
		CreateFolioConfig.Builder builder = new CreateFolioConfig.Builder(FOLIO_NAME,MAGAZINE_TITLE,FOLIO_NUMBER,RESOLUTION_WIDTH,RESOLUTION_HEIGHT);
		
		CreateFolioConfig actualFolioRequest;
		
		actualFolioRequest = builder.withBindingRight(BINDING_RIGHT)
								.withFolioDescription(FOLIO_DESCRIPTION)
								.withPublicationDate(PUBLICATION_DATE)
								.withCoverDate(COVER_DATE)
								.withLocked(LOCKED)
								.withDefaultAssetFormat(DEFAULT_ASSET_FORMAT)
								.withDefaultJPEGQuality(DEFAULT_JPG_QUALITY)
								.withDefaultAssetFormat(DEFAULT_ASSET_FORMAT)
								.withFolioIntent(FOLIO_INENT)
								.build();
		
		assertThat("Binding Right:",actualFolioRequest.isBindingRight(),equalTo(BINDING_RIGHT));	
		
		assertThat("Folio Description:",actualFolioRequest.getFolioDescription(),equalTo(FOLIO_DESCRIPTION));
		
		assertThat("Publication Date:",actualFolioRequest.getPublicationDate(),equalTo(PUBLICATION_DATE));
		
		assertThat("Cover date:",actualFolioRequest.getCoverDate(),equalTo(COVER_DATE));
		
		assertThat("Locked:",actualFolioRequest.isLocked(),equalTo(LOCKED));
		
		assertThat("Default Asset Format:",actualFolioRequest.getDefaultAssetFormat(),equalTo(DEFAULT_ASSET_FORMAT));
				
		assertThat("Default JPEG Quality:",actualFolioRequest.getDefaultJPEGQuality(),equalTo(DEFAULT_JPG_QUALITY));
	}
	
	@Test 
	public void testEqualness() {
		CreateFolioConfig.Builder builder = new CreateFolioConfig.Builder(FOLIO_NAME,MAGAZINE_TITLE,FOLIO_NUMBER,RESOLUTION_WIDTH,RESOLUTION_HEIGHT);
		
		builder =  builder.withBindingRight(BINDING_RIGHT)
								.withFolioDescription(FOLIO_DESCRIPTION)
								.withPublicationDate(PUBLICATION_DATE);

								
		CreateFolioConfig requestOne = builder.build();						
				
		CreateFolioConfig requestTwo = builder.build();
		
		assertThat("Should equal:",requestOne,equalTo(requestTwo));
		
		requestTwo = builder.withLocked(LOCKED).build();
		
		assertThat("Should not equal:",requestOne,not(equalTo(requestTwo)));				
	}
}
