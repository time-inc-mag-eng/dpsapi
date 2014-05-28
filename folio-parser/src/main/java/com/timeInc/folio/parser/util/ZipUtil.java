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

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/**
 * Utility class to zip a directory
 */
public class ZipUtil {
	
	private ZipParameters initParameters() {
		ZipParameters parameters = new ZipParameters(); 
		
		// set compression method to store compression
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);

		// Set the compression level
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
		return parameters;
	}

	/**
	 * Zip the specified source directory and put it in the destination directory
	 * with the specified name and extension.
	 *
	 * @param sourceDir the source dir
	 * @param destinationDir the destination dir
	 * @param zipName the zip name
	 * @param extension the extension
	 * @return the zipped file location
	 */
	public File zip(File sourceDir, File destinationDir, String zipName, String extension)  {
		checkIfDirectory(sourceDir);
		checkIfDirectory(destinationDir);
		
		ZipParameters parameters = initParameters();
		
		File outputFile = new File(destinationDir, zipName + extension);
		
		try {
			ZipFile zipFile = new ZipFile(outputFile);

			for(File file : sourceDir.listFiles()) {
				if(file.isDirectory()) {
					zipFile.addFolder(file, parameters);
				} else {
					zipFile.addFile(file, parameters);
				}
			}	
		} catch(ZipException ze) {
			throw new RuntimeException(ze);
		}
		
		return outputFile;
	}

	private void checkIfDirectory(File source) {
		if(!source.isDirectory())
			throw new IllegalArgumentException(source + " is not a directory");
	}

}
