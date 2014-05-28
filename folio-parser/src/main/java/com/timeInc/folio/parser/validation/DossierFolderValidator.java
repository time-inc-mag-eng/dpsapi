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
package com.timeInc.folio.parser.validation;

import java.io.File;
import java.util.List;

import com.timeInc.folio.parser.FolioMetaData;

/**
 * A validator that verifies if the dossier ids in FolioMetaData is
 * contained in the expanded folio.
 */
public class DossierFolderValidator implements FolioValidator {

	/* (non-Javadoc)
	 * @see com.timeInc.folio.parser.validation.FolioValidator#validate(com.timeInc.folio.parser.FolioMetaData, java.io.File)
	 */
	public Validation validate(FolioMetaData metaData, File contentDirectory) {
		List<String> folioIds = metaData.getContentStackIds();
		
		for(String id : folioIds) {
			File dossierIdDirectory = new File(contentDirectory,id);
			if(!dossierIdDirectory.exists())
				return Validation.getInvalid("Folder for dossier id " + id + " does not exist");
		}
		
		return Validation.getValid();
	}
}
