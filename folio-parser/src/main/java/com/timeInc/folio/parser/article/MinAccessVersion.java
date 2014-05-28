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
package com.timeInc.folio.parser.article;


import com.timeInc.folio.parser.FolioMetaData;
import com.timeInc.folio.parser.article.sidecar.ArticleAccess;
import com.timeInc.folio.parser.validation.Validation;

/**
 * 
 * Validates an article to see if the root folio's meta data has a target viewer at least gte to
 * the minimum access level of each article.
 * 
 */
public class MinAccessVersion implements ArticleValidator {

	
	/* (non-Javadoc)
	 * @see com.timeInc.folio.parser.article.ArticleValidator#validate(com.timeInc.folio.parser.FolioMetaData, com.timeInc.folio.parser.article.ArticleFolioMetaDeta)
	 */
	@Override
	public Validation validate(FolioMetaData folioMetaData,	ArticleFolioMetaData articleMetaData) {
		ArticleAccess access = articleMetaData.getAccess();
		String targetViewer = articleMetaData.getTargetViewer() == null ? "" : articleMetaData.getTargetViewer();
		
		if(targetViewer.compareTo(access.getMinTargetVersion()) < 0) {
			return Validation.getInvalid(access + " requires a targetViewer >= " + access.getMinTargetVersion());
		} else
			return Validation.getValid();
	}
}
