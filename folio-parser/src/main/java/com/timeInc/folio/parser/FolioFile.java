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
package com.timeInc.folio.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.timeInc.folio.parser.article.ArticleFile;

/**
 * Representation of a parsed folio file.
 */
public class FolioFile {
	private final Map<String,ArticleFile> articles;
	
	private final FolioMetaData metaData;
	
	private File htmlResource;
	
	/**
	 * Instantiates a new folio file.
	 *
	 * @param metaData the meta data
	 */
	public FolioFile(FolioMetaData metaData) {
		this.metaData = metaData;
		this.articles = new LinkedHashMap<String,ArticleFile>();
	}
	
	/**
	 * Sets the html resource.
	 *
	 * @param htmlResource the new html resource
	 */
	public void setHtmlResource(File htmlResource) {
		this.htmlResource = htmlResource;
	}
	
	/**
	 * Gets the html resource.
	 *
	 * @return the html resource
	 */
	public File getHtmlResource() {
		return htmlResource;
	}

	/**
	 * Adds the article file.
	 *
	 * @param id the id
	 * @param article the article
	 */
	public void addArticleFile(String id, ArticleFile article) {
		articles.put(id,article);
	}
	
	/**
	 * Gets the article file.
	 *
	 * @param id the id
	 * @return the article file
	 */
	public ArticleFile getArticleFile(String id)  {
		return articles.get(id);
	}
	
	/**
	 * Gets the article ids.
	 *
	 * @return the article ids
	 */
	public List<String> getArticleIds() {
		List<String> articleList = new ArrayList<String>();
		articleList.addAll(articles.keySet());
		return Collections.unmodifiableList(articleList);
	}
	
	/**
	 * Gets the article files.
	 *
	 * @return the article files
	 */
	public List<ArticleFile> getArticleFiles() {
		List<ArticleFile> articleList = new ArrayList<ArticleFile>();
		articleList.addAll(articles.values());
		return Collections.unmodifiableList(articleList);
	}
	
	/**
	 * Gets the folio meta data.
	 *
	 * @return the folio meta data
	 */
	public FolioMetaData getFolioMetaData() {
		return metaData;
	}
	
	/**
	 * Gets the max target version.
	 *
	 * @return the max target version
	 */
	public String getMaxTargetVersion() {
		String currentMax = null;
		
		// O(articles)
		for(ArticleFile articleFile : articles.values()) {
			String targetViewer = articleFile.getMetaData().getTargetViewer();
			
			if(compare(currentMax, targetViewer) < 0)
					currentMax = targetViewer;
		}
		
		return currentMax;
	}
	
	
	private int compare(String v1, String v2) {
		if(v1 == null && v2 == null)
			return 0;
		else if (v1 == null && v2 != null)
			return -1;
		else if(v1 != null && v2 == null)
			return 1;
		else {
			String s1 = normalisedVersion(v1);
			String s2 = normalisedVersion(v2);
			return s1.compareTo(s2);
		}
	}
	
	private static String normalisedVersion(String version) {
		return normalisedVersion(version, ".", 4);
	}

	private static String normalisedVersion(String version, String sep, int maxWidth) {
		String[] split = Pattern.compile(sep, Pattern.LITERAL).split(version);
		StringBuilder sb = new StringBuilder();
		for (String s : split) {
			sb.append(String.format("%" + maxWidth + 's', s));
		}
		return sb.toString();
	}
}
