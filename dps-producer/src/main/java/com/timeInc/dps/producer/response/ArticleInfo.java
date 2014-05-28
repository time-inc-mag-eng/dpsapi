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
 * The ArticleInfo data structure. See Adobe DPS folio documentation for more information.
 */
public class ArticleInfo {
	private final String id;
	private final String name;
	private final String type;
	private final ArticleMetaData articleMetadata;
	
	/**
	 * Instantiates a new article info.
	 *
	 * @param id the id
	 * @param name the name
	 * @param type the type
	 * @param articleMetadata the article metadata
	 */
	public ArticleInfo(String id, String name,
			String type, ArticleMetaData articleMetadata) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.articleMetadata = articleMetadata;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Gets the article metadata.
	 *
	 * @return the article metadata
	 */
	public ArticleMetaData getArticleMetadata() {
		return articleMetadata;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ArticleInfo [id=" + id + ", name=" + name + ", type=" + type
				+ ", articleMetaData=" + articleMetadata + "]";
	}


}
