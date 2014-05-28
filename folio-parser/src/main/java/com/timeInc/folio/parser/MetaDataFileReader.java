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
 * An interface that is able to read some metadata related to a folio. 
 * @param <T> the metadata about the folio
 */
public interface MetaDataFileReader<T> {
	
	/**
	 * Read the metadata
	 *
	 * @param file the expanded directory that contains the metadata
	 * @return the metadata
	 */
	T read(File file);
}
