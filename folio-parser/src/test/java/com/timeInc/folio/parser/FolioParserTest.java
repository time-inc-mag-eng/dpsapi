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

import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import com.timeInc.folio.parser.article.ArticleParser;
import com.timeInc.folio.parser.validation.FolioValidator;
import com.timeInc.folio.parser.validation.Validation;


public class FolioParserTest {
	@Mock
	private MetaDataFinder producer;

	@Mock
	private FolioValidator folioValidator;
	
	@Mock
	private ArticleParser articleParser;


	private File outputDirectory;

	private FolioParser parser;
	
	private List<String> contentStackId = Arrays.asList("10","11","12");
	private FolioMetaData metaData = new FolioMetaData("orientation",100,200,contentStackId,"id");
	
	private Validation valid = Validation.getValid();
	private Validation invalid = Validation.getInvalid("invalid");


	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery();

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

//
//	@Before
//	public void init() throws Exception {	
//		parser = new SingleFolioParser(producer,folioValidator,articleParser);
//
//		outputDirectory = folder.newFolder();
//	}
//
//
//	private File getRoot() throws Exception {
//		return folder.getRoot();
//	}
//
//
//
//
//	@Test
//	public void getsMetaDataUsingRootDirectory() throws Exception {
//		context.checking(new Expectations() {{ 		
//			allowing(folioValidator);
//			will(returnValue(valid));
//			
//			allowing(articleParser);
//			
//			exactly(1).of(producer).getMetaData(getRoot());
//			will(returnValue(metaData));
//		}}); 	
//
//		parser.parse(getRoot(),outputDirectory);
//	}
//
//	@Test
//	public void validatesFolio() throws Exception {
//		context.checking(new Expectations() {{ 		
//			allowing(producer).getMetaData(with(any(File.class)));
//			will(returnValue(metaData));
//			
//			allowing(articleParser);
//			
//			exactly(1).of(folioValidator).validate(with(any(FolioMetaData.class)),with(any(File.class)));
//			will(returnValue(valid));
//		}}); 	
//
//		parser.parse(getRoot(),outputDirectory);
//	}
//	
//	@Test(expected = ParserException.class)
//	public void failsIfValidationFailed() throws Exception {
//		context.checking(new Expectations() {{ 		
//			allowing(producer).getMetaData(with(any(File.class)));
//			will(returnValue(metaData));
//			
//			allowing(folioValidator).validate(with(any(FolioMetaData.class)),with(any(File.class)));
//			will(returnValue(invalid));
//		}}); 
//		
//		parser.parse(getRoot(),outputDirectory);
//	}
//	
//	@Test
//	public void invokesPackagerForEachDossierId() throws Exception{
//		context.checking(new Expectations() {{ 		
//			allowing(producer).getMetaData(with(any(File.class)));
//			will(returnValue(metaData));
//			
//			allowing(articleParser).setOutputDirectory(with(any(File.class)));
//			
//			allowing(folioValidator).validate(with(any(FolioMetaData.class)),with(any(File.class)));
//			will(returnValue(valid));	
//		}});
//		
//		for(final String ids : contentStackId) {
//			context.checking(new Expectations() {{ 		
//				exactly(1).of(articleParser).parse(new File(getRoot(),ids),metaData);
//			}});
//		}	
//		
//		parser.parse(getRoot(),outputDirectory);	
//	}
//	
//	@Test(expected = ParserException.class)
//	public void failsWhenArticleParserThrowsException() throws Exception {
//		context.checking(new Expectations() {{ 		
//			allowing(producer).getMetaData(with(any(File.class)));
//			will(returnValue(metaData));
//			
//			allowing(folioValidator).validate(with(any(FolioMetaData.class)),with(any(File.class)));
//			will(returnValue(valid));	
//			
//			allowing(articleParser).setOutputDirectory(with(any(File.class)));
//			
//			allowing(articleParser).parse(with(any(File.class)),with(any(FolioMetaData.class)));
//			will(throwException(new ParserException("an exception")));	
//		}});	
//		parser.parse(getRoot(),outputDirectory);	
//	}
	
	
}
