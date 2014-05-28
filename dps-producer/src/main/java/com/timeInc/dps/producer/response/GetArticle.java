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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The get list of articles response. See Adobe DPS folio documentation for more information.
 */
public class GetArticle extends Status {
	private final List<ArticleInfo> articles;

	
	/**
	 * Instantiates a new gets the article.
	 *
	 * @param articles the articles
	 * @param status the status
	 */
	public GetArticle(List<ArticleInfo> articles, String status) {
		super(status);
		this.articles = articles;
	}
	
	/**
	 * Gets the articles.
	 *
	 * @return the articles
	 */
	public List<ArticleInfo> getArticles() {
		return Collections.unmodifiableList(articles);
	}
	
	/**
	 * Gets the articles in order.
	 *
	 * @return the articles in order
	 */
	public List<ArticleInfo> getArticlesInOrder() {
		List<ArticleInfo> sortedArticles = new ArrayList<ArticleInfo>(getArticles());
		Collections.sort(sortedArticles, new InOrderArticle());
		
		return Collections.unmodifiableList(sortedArticles);
	}
	
	private static class InOrderArticle implements Comparator<ArticleInfo> {
		@Override
		public int compare(ArticleInfo o1, ArticleInfo o2) {
	        int o1Sort = o1.getArticleMetadata().getSortNumber();
	        int o2Sort = o2.getArticleMetadata().getSortNumber();
	        
	        return (o1Sort < o2Sort ? -1 : (o1Sort == o2Sort ? 0 : 1));
		}
	}

	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.response.Status#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) { return EqualsBuilder.reflectionEquals(this, obj); }
	
	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.response.Status#hashCode()
	 */
	@Override
	public int hashCode() { return HashCodeBuilder.reflectionHashCode(this); }	
	
	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.response.Status#toString()
	 */
	@Override
	public String toString() { return ToStringBuilder.reflectionToString(this); }

}
