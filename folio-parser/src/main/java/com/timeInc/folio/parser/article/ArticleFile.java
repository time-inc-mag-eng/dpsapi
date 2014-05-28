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

import java.io.File;

/**
 * The location of a folio article and its corresponding
 * meta data.
 */
public class ArticleFile {
	private final File article;
	
	private final ArticleFolioMetaData metaData;
	
	/**
	 * Instantiates a new article file.
	 *
	 * @param article the article
	 * @param metaData the meta data
	 */
	public ArticleFile(File article, ArticleFolioMetaData metaData) {
		this.article = article;
		this.metaData = metaData;
	}

	/**
	 * Gets the location of the article.
	 *
	 * @return the path to the article
	 */
	public File getArticle() {
		return article;
	}

	/**
	 * Gets the metadata of article
	 *
	 * @return the article's metadata
	 */
	public ArticleFolioMetaData getMetaData() {
		return metaData;
	}
}
