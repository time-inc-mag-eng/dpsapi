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

import com.timeInc.folio.parser.article.ArticleFile;
import com.timeInc.folio.parser.article.ArticleParser;
import com.timeInc.folio.parser.exception.ParserException;
import com.timeInc.folio.parser.validation.FolioValidator;
import com.timeInc.folio.parser.validation.Validation;


/**
 * A folio file parser that that validates the folio and delegates the parsing of the article it contains to
 * an {@code com.timeInc.folio.parser.article.ArticleParser} so that it can form a {@code FolioFile} object.
 */
public class SingleFolioParser implements FolioParser {
	private final FolioValidator validator;
	
	private final ArticleParser articleParser;
	
	private File folioDirectory;
	
	/**
	 * Instantiates a new single folio parser.
	 *
	 * @param validator the validator
	 * @param articleParser the article parser
	 */
	public SingleFolioParser(FolioValidator validator, ArticleParser articleParser) {
		this.validator = validator;
		this.articleParser = articleParser;
	}

	/* (non-Javadoc)
	 * @see com.timeInc.folio.parser.FolioParser#parse(com.timeInc.folio.parser.FolioMetaData, java.io.File)
	 */
	@Override
	public FolioFile parse(FolioMetaData data, File folioDirectory) {
		this.folioDirectory = folioDirectory;
		
		validate(data);

		FolioFile folioFile = new FolioFile(data);
		
		for(String ids : data.getContentStackIds()) {
			File articlePath = new File(folioDirectory, ids);
			
			if(articlePath.exists()) {
				ArticleFile parsedArticle  = articleParser.parse(articlePath,data);
				folioFile.addArticleFile(ids,parsedArticle);
			}
		}
		
		File htmlDir = new File(folioDirectory, data.getHtmlResourceFolderName());
		
		if(htmlDir.isDirectory()) {
			folioFile.setHtmlResource(htmlDir);		
		}
		
		return folioFile;
	}

	/**
	 * Validate.
	 *
	 * @param data the data
	 */
	private void validate(FolioMetaData data) {
		Validation info = validator.validate(data, folioDirectory);

		if(!info.isValid())
			throw new ParserException("Folio validation failed:" + info.getErrorMsg());
	}
}
