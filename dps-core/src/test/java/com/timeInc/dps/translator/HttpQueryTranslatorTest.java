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
package com.timeInc.dps.translator;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.timeInc.dps.translator.HttpParameterTranslator;
import com.timeInc.dps.translator.TranslatableRequest;



public class HttpQueryTranslatorTest {
	private static final String EXPECTED_BASE_QUERY = "booleanValue=true&charValue=c&intValue=100";
	
	private final HttpParameterTranslator translator = new HttpParameterTranslator();
	
	@Test
	public void translatesPrimitiveTypesToAHttpQueryString() {
		translationAreEqualFor(new SimplePrimitive(),EXPECTED_BASE_QUERY);
	}
	
	@Test
	public void translatePropertiesInParentClass() {
		String expectedQuery = EXPECTED_BASE_QUERY + "&intValue=20";
		
		translationAreEqualFor(new ExtendedPrimitive(),expectedQuery);
	}
	
	@Test
	public void translateStringToAHttpQueryString() {
		String expectedQuery = EXPECTED_BASE_QUERY + "&strValue=helloworld";
			
		translationAreEqualFor(new PrimitiveWithString(),expectedQuery);
	}
	
	@Test
	public void translateObjectUsingToString() {
		String expectedQuery = EXPECTED_BASE_QUERY + "&objectValue=custom";
		
		translationAreEqualFor(new PrimitiveWithObject(),expectedQuery);
	}
	
	@Test
	public void doesNotIncludeNullObjects() {
		translationAreEqualFor(new PrimitiveWithNull(),EXPECTED_BASE_QUERY);
	}
	
	@Test
	public void httpParamEncodesString() {
		String expectedQuery = EXPECTED_BASE_QUERY + "&encodedVal=%26a";
		
		translationAreEqualFor(new PrimitiveWithEncoding(),expectedQuery);
	}
	
	
	private void translationAreEqualFor(TranslatableRequest objectToTranslate, String expectedTranslation) {
		String actualTranslation = translator.convertToString(objectToTranslate);
		assertThat(actualTranslation,equalTo(expectedTranslation));
	}
	
	
	private static class SimplePrimitive implements TranslatableRequest { 
		private boolean booleanValue = true;
		private char charValue = 'c';
		private int intValue = 100;
	}
	
	private static class ExtendedPrimitive extends SimplePrimitive {
		private int intValue = 20;
	}
	
	private static class PrimitiveWithString extends SimplePrimitive {
		private String strValue = "helloworld";
	}
	
	private static class PrimitiveWithObject extends SimplePrimitive {
		
		private CustomObject objectValue = new CustomObject();
		
		private static class CustomObject {
			@Override 
			public String toString() {
				return "custom";
			}
		}
	}
	
	private static class PrimitiveWithNull extends SimplePrimitive {
		private Object obj = null;
	}
	
	private static class PrimitiveWithEncoding extends SimplePrimitive {
		private String encodedVal = "&a";
	}
	
	
	
}
