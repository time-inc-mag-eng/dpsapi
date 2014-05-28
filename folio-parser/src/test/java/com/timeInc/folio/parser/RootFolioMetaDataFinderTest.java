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

import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.timeInc.folio.parser.RootFolioMetaDataFinder;



public class RootFolioMetaDataFinderTest {
	private final String metaDataFileName = "Folio.xml";

	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery();

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	private RootFolioMetaDataFinder producer;

	File pathToMetaData;


	@Before
	public void init() throws Exception {	

		pathToMetaData = folder.newFile(metaDataFileName);
	}

	@Test
	@Ignore
	public void invokesReaderWithMetaFileInRoot() {
		
//		producer.getMetaData(folder.getRoot());
	}
}

