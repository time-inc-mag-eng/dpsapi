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

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.timeInc.folio.parser.FolioMetaData;

public class DossierFolderValidatorTest {
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();	

	private DossierFolderValidator validator;
	private File basePathToFolio;
	
	private List<String> contentStackId = Arrays.asList("10","11","12");
	private FolioMetaData metaData = new FolioMetaData("orientation",100,200,contentStackId,"id");
	
	
	@Before
	public void init() throws Exception {
		basePathToFolio = folder.newFolder();
		validator = new DossierFolderValidator();
	}
			
	@Test
	@Ignore
	public void invalidWhenNoneOfFolderNamedDossierIdsExistInRoot() {
		assertInvalid(validator.validate(metaData,basePathToFolio),any(String.class));
	}
	
	@Test
	public void invalidWhenAtLeastOneOfFolderNamedDossierIdsDoesNotExistInRoot() {
		createDirs("10","11");
		assertInvalid(validator.validate(metaData,basePathToFolio),containsString("12"));
	}
	
	@Test
	public void validWhenAllFoldersWithNameOfDossierIdsExists() {
		createDirs("10","11","12");
		assertValid(validator.validate(metaData,basePathToFolio));
	}

	private void createDirs(String... folderNames) {
		for(String folderName : folderNames) {
			new File(basePathToFolio,folderName).mkdirs();
		}
	}
	
	private void assertInvalid(Validation info, Matcher<String> errorMsgMatcher) {
		assertThat(info.getErrorMsg(),errorMsgMatcher);
		assertFalse("Is not invalid",info.isValid());
	}
	
	private void assertValid(Validation info) {
		assertTrue(info.isValid());
	}
	
}
