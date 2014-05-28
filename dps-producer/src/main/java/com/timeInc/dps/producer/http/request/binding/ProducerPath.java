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
package com.timeInc.dps.producer.http.request.binding;

/**
 * The Class ProducerPath.
 */
public final class ProducerPath {
	private static final String UPLOAD_ARTICLE = "/folios/%s/articles";
	private static final String GET_FOLIOS = "/folios/";
	private static final String OPEN_SESSION = "/sessions";
	private static final String DELETE_SESSION = "/sessions";
	private static final String UPDATE_FOLIO = "/folios/%s";
	private static final String PREVIEW = "/folios/%s/previews/%s";
	private static final String CREATE_FOLIO = "/folios";
	private static final String DELETE_ARTICLE = "/folios/%s/articles/%s";
	private static final String FOLIO_INFO = "/folios/%s";
	private static final String UPLOAD_HTML = "/folios/%s/htmlresources";
	private static final String UPDATE_ARTICLE = "/folios/%s/articles/%s/metadata";
	private static final String ARTICLE_LIST = "/folios/%s/articles";
	private static final String DELETE_FOLIO = "/folios/%s";

	private final String path;

	private ProducerPath(String path) {
		this.path = path;
	}

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * Update article.
	 *
	 * @param folioId the folio id
	 * @param articleId the article id
	 * @return the producer path
	 */
	public static ProducerPath updateArticle(String folioId, String articleId) {
		return new ProducerPath(String.format(UPDATE_ARTICLE,folioId,articleId));
	}
	
	/**
	 * Open session.
	 *
	 * @return the producer path
	 */
	public static ProducerPath openSession() {
		return new ProducerPath(OPEN_SESSION);
	}

	/**
	 * Delete session.
	 *
	 * @return the producer path
	 */
	public static ProducerPath deleteSession() {
		return new ProducerPath(DELETE_SESSION);
	}
	
	/**
	 * New server.
	 *
	 * @return the producer path
	 */
	public static ProducerPath newServer() {
		return new ProducerPath(OPEN_SESSION);
	}


	/**
	 * Creates the folio.
	 *
	 * @return the producer path
	 */
	public static ProducerPath createFolio() {
		return new ProducerPath(CREATE_FOLIO);
	}
	
	/**
	 * Delete article.
	 *
	 * @param folioId the folio id
	 * @param articleId the article id
	 * @return the producer path
	 */
	public static ProducerPath deleteArticle(String folioId, String articleId) {
		return new ProducerPath(String.format(DELETE_ARTICLE,folioId,articleId));
	}
	
	/**
	 * Delete folio.
	 *
	 * @param folioId the folio id
	 * @return the producer path
	 */
	public static ProducerPath deleteFolio(String folioId) {
		return new ProducerPath(String.format(DELETE_FOLIO,folioId));
	}
	

	/**
	 * Upload article.
	 *
	 * @param folioId the folio id
	 * @return the producer path
	 */
	public static ProducerPath uploadArticle(String folioId) {
		return new ProducerPath(String.format(UPLOAD_ARTICLE,folioId));
	}


	/**
	 * Update folio.
	 *
	 * @param folioId the folio id
	 * @return the producer path
	 */
	public static ProducerPath updateFolio(String folioId) {
		return new ProducerPath(String.format(UPDATE_FOLIO,folioId));
	}

	/**
	 * Upload landscape preview.
	 *
	 * @param folioId the folio id
	 * @return the producer path
	 */
	public static ProducerPath uploadLandscapePreview(String folioId) {
		return new ProducerPath(String.format(PREVIEW,folioId,"landscape"));
	}


	/**
	 * Upload portrait preview.
	 *
	 * @param folioId the folio id
	 * @return the producer path
	 */
	public static ProducerPath uploadPortraitPreview(String folioId) {
		return new ProducerPath(String.format(PREVIEW,folioId,"portrait"));
	}
	
	/**
	 * Download landscape preview.
	 *
	 * @param folioId the folio id
	 * @return the producer path
	 */
	public static ProducerPath downloadLandscapePreview(String folioId) {
		return uploadLandscapePreview(folioId);
	}
	
	/**
	 * Download portrait preview.
	 *
	 * @param folioId the folio id
	 * @return the producer path
	 */
	public static ProducerPath downloadPortraitPreview(String folioId) {
		return uploadPortraitPreview(folioId);
	}
	
	/**
	 * Article list.
	 *
	 * @param folioId the folio id
	 * @return the producer path
	 */
	public static ProducerPath articleList(String folioId) {
		return new ProducerPath(String.format(ARTICLE_LIST,folioId));
	}
	
	
	/**
	 * Folios info.
	 *
	 * @return the producer path
	 */
	public static ProducerPath foliosInfo() {
		return new ProducerPath(GET_FOLIOS);
	}	
	

	/**
	 * Folio info.
	 *
	 * @param folioId the folio id
	 * @return the producer path
	 */
	public static ProducerPath folioInfo(String folioId) {
		return new ProducerPath(String.format(FOLIO_INFO,folioId));
	}

	/**
	 * Upload html resource.
	 *
	 * @param folioId the folio id
	 * @return the producer path
	 */
	public static ProducerPath uploadHtmlResource(String folioId) {
		return new ProducerPath(String.format(UPLOAD_HTML,folioId));
	}

	/**
	 * Delete html resource.
	 *
	 * @param folioId the folio id
	 * @return the producer path
	 */
	public static ProducerPath deleteHtmlResource(String folioId) {
		return new ProducerPath(String.format(UPLOAD_HTML,folioId));
	}
}
