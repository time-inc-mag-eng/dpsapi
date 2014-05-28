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


/**
 * Reads a folio's xml meta data file.
 */
public class XmlFolioMetaDataReader extends AbstractXmlFolio<FolioMetaData>  {
	
	/* (non-Javadoc)
	 * @see com.timeInc.folio.parser.util.AbstractXmlReader#getMetaDataAfterXmlLoaded()
	 */
	@Override
	protected FolioMetaData getMetaDataAfterXmlLoaded() {
		return new FolioMetaData(getOrientation(), getWidth(), getHeight(), getArticles(), getId());
	}
}
