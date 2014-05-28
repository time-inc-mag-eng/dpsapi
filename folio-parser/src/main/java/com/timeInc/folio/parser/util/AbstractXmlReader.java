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
package com.timeInc.folio.parser.util;

import java.io.File;

import com.timeInc.folio.parser.MetaDataFileReader;

/**
 * A base class that is used to load an xml file and then
 * delegate to the subclass after the xml file is loaded.
 *
 * @param <T> the metadata about the folio
 */
public abstract class AbstractXmlReader<T> implements MetaDataFileReader<T> {
	
	protected final SimpleXpathReader xPathReader;
	
	/**
	 * Instantiates a new abstract xml reader.
	 *
	 * @param xPathReader the xpath reader
	 */
	public AbstractXmlReader(SimpleXpathReader xPathReader) {
		this.xPathReader = xPathReader;
	}
	
	/**
	 * Instantiates a new abstract xml reader.
	 */
	public AbstractXmlReader() {
		this(new SimpleXpathReader());
	}
	
	/* (non-Javadoc)
	 * @see com.timeInc.folio.parser.MetaDataFileReader#read(java.io.File)
	 */
	@Override
	public final T read(File file) {
		xPathReader.loadXml(file);
		return getMetaDataAfterXmlLoaded();
	}
	
	/**
	 * Gets the metadata after xml loaded.
	 *
	 * @return the metadata after xml loaded
	 */
	protected abstract T getMetaDataAfterXmlLoaded();
	
}
