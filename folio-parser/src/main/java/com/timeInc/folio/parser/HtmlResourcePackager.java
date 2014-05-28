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

import com.timeInc.folio.parser.util.ZipUtil;
/**
 * Zips the HTMLResource directory.
 */
public class HtmlResourcePackager implements Packager {
	private static final String FILE_EXTENSION = ".zip";
	
	private static final String EXPECTED_HTML_ZIP_NAME = "HTMLResources";
	
	private final ZipUtil zipUtil;

	/**
	 * Instantiates a new html resource packager.
	 *
	 * @param zipUtil the zip util
	 */
	public HtmlResourcePackager(ZipUtil zipUtil) {
		this.zipUtil = zipUtil;
	}

	/* (non-Javadoc)
	 * @see com.timeInc.folio.parser.Packager#pack(java.io.File, java.io.File)
	 */
	@Override
	public File pack(File sourceDir, File destinationDir)  {
		return zipUtil.zip(sourceDir, destinationDir, EXPECTED_HTML_ZIP_NAME, FILE_EXTENSION);
	}
}
