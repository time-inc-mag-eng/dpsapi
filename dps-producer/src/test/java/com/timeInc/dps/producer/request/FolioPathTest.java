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
package com.timeInc.dps.producer.request;

import org.junit.Test;

import com.timeInc.dps.producer.http.request.binding.ProducerPath;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class FolioPathTest {

	@Test
	public void uploadHtmlResourcePath() {
		ProducerPath uploadHtml = ProducerPath.uploadHtmlResource("afolioId");
		assertThat(uploadHtml.getPath(),equalTo("/folios/afolioId/htmlresources"));
	}
	
	@Test
	public void deleteHtmlResourcesPath() {
		ProducerPath deleteHtml = ProducerPath.deleteHtmlResource("afolioId");
		assertThat(deleteHtml.getPath(),equalTo("/folios/afolioId/htmlresources"));
	}

	@Test
	public void openSessionPath() {
		ProducerPath openSessionPath = ProducerPath.openSession();
		assertThat(openSessionPath.getPath(),equalTo("/sessions"));
	}

	@Test
	public void uploadArticlePath() {
		ProducerPath uploadArticlePath = ProducerPath.uploadArticle("afolioid");
		assertThat(uploadArticlePath.getPath(),equalTo("/folios/afolioid/articles"));
	}

	@Test
	public void deleteSessionPath() {
		ProducerPath deleteSessionPath = ProducerPath.deleteSession();
		assertThat(deleteSessionPath.getPath(),equalTo("/sessions"));
	}

	@Test
	public void updateFolioPath() {
		ProducerPath updateFolioPath = ProducerPath.updateFolio("afolioid");
		assertThat(updateFolioPath.getPath(),equalTo("/folios/afolioid"));
	}

	@Test
	public void uploadFolioPreviewLandscapePath() {
		ProducerPath uploadFolioPreview = ProducerPath.uploadLandscapePreview("afolioid");
		assertThat(uploadFolioPreview.getPath(),equalTo("/folios/afolioid/previews/landscape"));
	}

	@Test
	public void uploadFolioPreviewPortraitPath() {
		ProducerPath uploadFolioPreview = ProducerPath.uploadPortraitPreview("afolioid");
		assertThat(uploadFolioPreview.getPath(),equalTo("/folios/afolioid/previews/portrait"));
	}


	@Test
	public void createFolioPath() {
		ProducerPath createFolioPath = ProducerPath.createFolio();
		assertThat(createFolioPath.getPath(),equalTo("/folios"));
	}
	
	@Test
	public void newServerPath() {
		ProducerPath createFolioPath = ProducerPath.newServer();
		assertThat(createFolioPath.getPath(),equalTo("/sessions"));
	}
	
	@Test
	public void deleteArticlePath() {
		ProducerPath deleteArticlePath = ProducerPath.deleteArticle("folioId","articleId");
		assertThat(deleteArticlePath.getPath(),equalTo("/folios/folioId/articles/articleId"));
	}
	
	@Test
	public void getFolioDataPath() {
		ProducerPath folioDataPath = ProducerPath.folioInfo("folioId");
		assertThat(folioDataPath.getPath(),equalTo("/folios/folioId"));
	}
	

}
