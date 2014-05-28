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

import com.timeInc.folio.parser.FolioMetaData;

/**
 * A folio validator
 */
public interface FolioValidator {
	
	/**
	 * Validates an expanded folio file based 
	 * on @see com.timeInc.folio.parser.FolioMetaData
	 *
	 * @param metaData the metadata of the folio 
	 * @param contentDirectory the expanded folio file
	 * @return the validation details
	 */
	Validation validate(FolioMetaData metaData, File contentDirectory);
}
