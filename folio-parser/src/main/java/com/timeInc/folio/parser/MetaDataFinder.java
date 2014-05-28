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

/**
 * Finds a the metadata file for an article or folio.
 */
public interface MetaDataFinder {
	
	/**
	 * Gets the meta data file.
	 *
	 * @param basePath the base path to look in
	 * @return the meta data file
	 */
	File getMetaDataFile(File basePath);
}
