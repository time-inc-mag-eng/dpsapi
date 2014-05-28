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

import com.timeInc.folio.parser.exception.ParserException;


/**
 * Uses Folio.xml (case sensitive) file as the metadata for a folio
 */
public class RootFolioMetaDataFinder implements MetaDataFinder {
	public final static String FOLIO_FILE = "Folio.xml";
	
	/* (non-Javadoc)
	 * @see com.timeInc.folio.parser.MetaDataFinder#getMetaDataFile(java.io.File)
	 */
	@Override
	public File getMetaDataFile(File basePath) {
		
		File metaDataFile = getFolioFileRelativeTo(basePath);

		if(!metaDataFile.isFile()) {
			throw new ParserException("Folio meta data file does not exist in root - " +  basePath + " :" + FOLIO_FILE);
		} else
			return metaDataFile;
	}

	private static File getFolioFileRelativeTo(File basePath) {
		return new File(basePath, FOLIO_FILE);
	}
}
