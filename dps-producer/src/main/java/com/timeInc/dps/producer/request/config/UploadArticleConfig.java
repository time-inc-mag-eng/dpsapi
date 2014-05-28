/*
 * 
 */
package com.timeInc.dps.producer.request.config;

import java.io.File;

/**
 * The upload article request. See Adobe DPS folio documentation for more information.
 */
public class UploadArticleConfig extends UploadAssetConfig {

	private final Integer sortOrder;
	private final String name;
	
	/**
	 * Instantiates a new upload article config.
	 *
	 * @param folioId the folio id
	 * @param article the article
	 * @param sortOrder the sort order
	 * @param name the name
	 */
	public UploadArticleConfig(String folioId, File article, Integer sortOrder, String name) {
		super(folioId, article);
		this.sortOrder = sortOrder;
		this.name = name;
	}
	
	/**
	 * Instantiates a new upload article config.
	 *
	 * @param folioId the folio id
	 * @param article the article
	 * @param sortOrder the sort order
	 */
	public UploadArticleConfig(String folioId, File article, Integer sortOrder) {
		this(folioId,article,sortOrder,null);
	}
	
	/**
	 * Instantiates a new upload article config.
	 *
	 * @param folioId the folio id
	 * @param article the article
	 */
	public UploadArticleConfig(String folioId, File article) {
		this(folioId,article,null,null);
	}
	
	
	/**
	 * Gets the sort order.
	 *
	 * @return the sort order
	 */
	public Integer getSortOrder() {
		return sortOrder;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}
