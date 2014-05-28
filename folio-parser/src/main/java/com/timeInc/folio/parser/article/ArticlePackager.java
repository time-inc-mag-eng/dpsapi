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

import com.timeInc.folio.parser.Packager;
import com.timeInc.folio.parser.util.ZipUtil;

/**
 * Packages an expanded article directory by zipping it.
 */
public class ArticlePackager implements Packager {
	private static final String FILE_EXTENSION = ".folio";
	
	private final ZipUtil zipUtil;

	/**
	 * Instantiates a new article packager.
	 *
	 * @param zipUtil the zip util
	 */
	public ArticlePackager(ZipUtil zipUtil) {
		this.zipUtil = zipUtil;
	}

	/*
	 * @see com.timeInc.folio.parser.Packager#pack(java.io.File, java.io.File)
	 * @return the zipped article location
	 */
	@Override
	public File pack(File sourceDir, File destinationDir)  {
		return zipUtil.zip(sourceDir,destinationDir,getDirectoryName(sourceDir),FILE_EXTENSION);
	}

	private static String getDirectoryName(File source) {
		int dirNameStartIndex = source.getAbsolutePath().lastIndexOf(File.separator) + 1;
		return source.getAbsolutePath().substring(dirNameStartIndex);
	}
}
