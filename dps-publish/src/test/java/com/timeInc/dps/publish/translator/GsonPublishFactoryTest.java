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
package com.timeInc.dps.publish.translator;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.timeInc.dps.publish.response.Result;

public class GsonPublishFactoryTest {

	@Test
	public void usesEnumToStringRepresentationToSerialize() {
		Gson gson = GsonPublishFactory.getInstance();
		
		String expectedJson = "\"ll\"";
		String actualJson = gson.toJson(TestStringEnum.VALUE_1);
		
		assertThat(actualJson,equalTo(expectedJson));
	}
	
	@Test
	public void usesToStringToEnumToDeserialize() {
		Gson gson = GsonPublishFactory.getInstance();
		
		String incomingJson = "\"22\"";
		
		TestStringEnum actual = gson.fromJson(incomingJson,TestStringEnum.class);
		
		assertThat(actual,equalTo(TestStringEnum.VALUE_2));
	}
	
	
	@Test(expected = JsonSyntaxException.class)
	public void failsIfNoMatchingStringRepresentationFound() {
		Gson gson = GsonPublishFactory.getInstance();
		String incomingJson = "\"21\"";
		gson.fromJson(incomingJson,TestStringEnum.class);
	}
	
	
	
	private enum TestStringEnum {
		VALUE_1("ll"),
		VALUE_2("22");
		
		private final String shortName;
		
		private TestStringEnum(String shortName) {
			this.shortName = shortName;
		}
		
		@Override
		public String toString() {
			return shortName;
		}
		
	}
	
}
