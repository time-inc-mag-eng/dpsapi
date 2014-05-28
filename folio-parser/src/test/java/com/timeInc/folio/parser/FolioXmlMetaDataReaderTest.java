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
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Ignore;
import org.junit.Test;

import com.timeInc.folio.parser.FolioMetaData;
import com.timeInc.folio.parser.AbstractXmlFolio;

public class FolioXmlMetaDataReaderTest {
	private final File FOLIO_FILE = new File("C:\\health\\folio.xml");
	private XmlFolioMetaDataReader reader = new XmlFolioMetaDataReader();
	
	private List<String> expectedArticles = Arrays.asList("7541","7565","7563","7607");
	private FolioMetaData expected = new FolioMetaData("portrait",1024,768,expectedArticles,"July/Aug Test");
	
	
	@Test
	@Ignore
	public void correctlyReadsAValidFolioXMLFile() throws Exception {
		FolioMetaData actual = reader.read(FOLIO_FILE);
		
		assertThat(actual,is(expected));	
	}
}
