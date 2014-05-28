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

import com.timeInc.folio.parser.FolioMetaData;
import com.timeInc.folio.parser.MetaDataFileReader;
import com.timeInc.folio.parser.MetaDataFinder;
import com.timeInc.folio.parser.article.sidecar.ArticleAccess;
import com.timeInc.folio.parser.exception.ParserException;
import com.timeInc.folio.parser.validation.Validation;

/**
 * Parses an article by taking into consideration the generated sidecar file.
 */
public class SingleArticleParser implements ArticleParser {	
	private ArticleValidator validator;
	
	private final MetaDataFinder mDataProducer;
	
	private final MetaDataFileReader<ArticleFolioMetaData> articleReader;
	
	private final MetaDataFinder sideCarFinder;
	
	private final MetaDataFileReader<ArticleAccess> sideCarReader;
	
	/**
	 * Instantiates a new single article parser with no validator.
	 *
	 * @param folioFinder the folio finder
	 * @param articleReader the article reader
	 * @param sideCarFinder the side car finder
	 * @param sideCarReader the side car reader
	 */
	public SingleArticleParser(MetaDataFinder folioFinder, MetaDataFileReader<ArticleFolioMetaData> articleReader,
			MetaDataFinder sideCarFinder, MetaDataFileReader<ArticleAccess> sideCarReader) {
		
		this(folioFinder, articleReader, sideCarFinder, sideCarReader, null);
	}
	
	/**
	 * Instantiates a new single article parser.
	 *
	 * @param folioFinder the folio finder
	 * @param articleReader the article reader
	 * @param sideCarFinder the side car finder
	 * @param sideCarReader the side car reader
	 * @param validator the article validator
	 */
	public SingleArticleParser(MetaDataFinder folioFinder, MetaDataFileReader<ArticleFolioMetaData> articleReader,
			MetaDataFinder sideCarFinder, MetaDataFileReader<ArticleAccess> sideCarReader, ArticleValidator validator) {
		
		this.mDataProducer = folioFinder;
		this.articleReader = articleReader;
		
		this.validator = validator;
		
		this.sideCarFinder = sideCarFinder;
		this.sideCarReader = sideCarReader;
	}

	/* Parses an article setting the access level to {@link com.timeInc.folio.parser.article.sidecar.ArticleAccess.NONE}
	 * if no associated sidecar is found. Otherwise, it uses the access level in the sidecar.
	 * @see com.timeInc.folio.parser.article.ArticleParser#parse(java.io.File, com.timeInc.folio.parser.FolioMetaData)
	 */
	@Override
	public ArticleFile parse(File pathToArticle, FolioMetaData folioMetaData) {
		ArticleFolioMetaData articleMetaData = articleReader.read(mDataProducer.getMetaDataFile(pathToArticle));
		
		File sideCarFile = sideCarFinder.getMetaDataFile(pathToArticle);
		
		if(sideCarFile != null) {
			ArticleAccess acccess = sideCarReader.read(sideCarFile);
			articleMetaData.setAccess(acccess);
		} else
			articleMetaData.setAccess(ArticleAccess.NONE);
		
		validate(folioMetaData,articleMetaData);
		
		return new ArticleFile(pathToArticle, articleMetaData);
	}
	
	private void validate(FolioMetaData folioMetaData, ArticleFolioMetaData articleMetaData) {
		if(validator != null) {
			Validation validationInfo = validator.validate(folioMetaData, articleMetaData);
			if(!validationInfo.isValid())
				throw new ParserException("Article: " + articleMetaData.getId() + " - " + validationInfo.getErrorMsg());
		}
	}

	/* (non-Javadoc)
	 * @see com.timeInc.folio.parser.article.ArticleParser#setArticleValidator(com.timeInc.folio.parser.article.ArticleValidator)
	 */
	@Override
	public void setArticleValidator(ArticleValidator validator) {
		this.validator = validator;
	}
}
