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
import java.util.Arrays;
import java.util.List;

import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import com.timeInc.folio.parser.FolioMetaData;
import com.timeInc.folio.parser.MetaDataFinder;
import com.timeInc.folio.parser.Packager;
import com.timeInc.folio.parser.validation.Validation;

public class SingleArticleParserTest {
	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery();
	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Mock
	Packager packager;

	@Mock 
	MetaDataFinder producer;
	
	@Mock 
	ArticleValidator validator;


	private SingleArticleParser parser;
	
	private List<String> contentStackId = Arrays.asList("10","11","12");
	private FolioMetaData rootMetaData = new FolioMetaData("orientation",100,200,contentStackId,"id");
	
	private Validation validationInfo = Validation.getValid();
	
	
	private List<String> articleDossierId = Arrays.asList("10");
	private FolioMetaData articleMetaData = new FolioMetaData("orientation",100,200,articleDossierId,"id");
	
	private File outputDirectory;
	

//	@Before
//	public void init() throws Exception {
//		parser = new SingleArticleParser(packager,producer);
//
//		outputDirectory = folder.newFolder();
//		
//	}
//
//
//
//	@Test(expected = IllegalArgumentException.class)
//	public void failsIfOutputDirectoryIsNotDirectory() {
//		parser.setOutputDirectory(new File(""));
//	}
//
//
//	@Test
//	public void invokesProducerWithRootToGetMetaData() throws Exception {
//		context.checking(new Expectations() {{ 		
//			
//			allowing(packager);
//			
//			exactly(1).of(producer).getMetaData(getRoot());
//			will(returnValue(articleMetaData));
//
//		}});
//		
//		parser.parse(getRoot(), rootMetaData);
//	}
//	
//	
//	@Test
//	public void invokesPackager() throws Exception {
//		context.checking(new Expectations() {{ 		
//			allowing(producer).getMetaData(with(any(File.class)));
//			
//			exactly(1).of(packager).pack(with(any(File.class)), with(any(File.class)));
//		}});
//		
//		parser.parse(getRoot(), rootMetaData);
//	}
//	
//	@Test
//	public void packsArticleUsingArticleDirectoryOutputtingToOutputDirectory() throws Exception {
//		context.checking(new Expectations() {{ 		
//			allowing(producer).getMetaData(with(any(File.class)));
//			
//			exactly(1).of(packager).pack(getRoot(),outputDirectory);
//		}});
//		
//		
//		parser.setOutputDirectory(outputDirectory);
//		parser.parse(getRoot(), rootMetaData);		
//	}
//	
//	@Test
//	public void invokesValidatorIfSet() throws Exception {
//		context.checking(new Expectations() {{ 		
//			allowing(producer).getMetaData(with(any(File.class)));
//			will(returnValue(articleMetaData));
//			
//			allowing(packager);
//			
//			exactly(1).of(validator).validate(rootMetaData, articleMetaData);
//			will(returnValue(validationInfo));
//		}});
//		
//		
//		parser.setArticleValidator(validator);
//		parser.parse(getRoot(), rootMetaData);	
//	}
//	
//	
//	private File getRoot() throws Exception {
//		return folder.getRoot();
//	}
	
}
