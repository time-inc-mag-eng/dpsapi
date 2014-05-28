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
package integration.com.timeInc.dps.translator;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.junit.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.timeInc.dps.translator.JsonTranslator;
import com.timeInc.dps.translator.TranslatableRequest;
import com.timeInc.dps.translator.TranslatableResponse;


/**
 * Integration test to see if Gson library is used as expected
 * @author tchamnongvongse1271
 *
 */

public class JsonTranslatorTest {

	private final JsonTranslator translator = new JsonTranslator();


	@Test
	public void canTranslateToJson() {		
		Config expectedObject = new Config("x@x.com","password",false);
		String expectedJsonValue = "{\"email\":\"x@x.com\", \"password\":\"password\", \"needToken\": false}";
		
		String translatedJson = translator.convertToString(expectedObject);
		assertJsonEquals(translatedJson,expectedJsonValue);
	}


	@Test
	public void translateAnObjectWithAnEnumUsingItsEnumName() {
		String translatedJson = translator.convertToString(new EnumTest());
		String expectedJsonvalue = "{\"test\":\"HighQuality\"}";

		assertThat(translatedJson,equalToIgnoringWhiteSpace(expectedJsonvalue));
	}


	
	public static void assertJsonEquals(String actualJson, String expectedJson) {
		JsonParser jsonParser = new JsonParser();
		
		JsonElement expected = jsonParser.parse(actualJson);
		JsonElement actual = jsonParser.parse(expectedJson);
		
		assertThat(expected,equalTo(actual));
	}
	
	
	private static class Config implements TranslatableResponse, TranslatableRequest {
		String email;
		String password;
		boolean needToken;
		
		public Config(String email, String password, boolean needToken) {
			super();
			this.email = email;
			this.password = password;
			this.needToken = needToken;
		}

		@Override
		public int hashCode() {
			return HashCodeBuilder.reflectionHashCode(this);
		}
		
		@Override
		public boolean equals(Object obj) {
			return EqualsBuilder.reflectionEquals(this,obj);
		}
		
	}


	private static class EnumTest implements TranslatableResponse, TranslatableRequest {
		StringEnum test = StringEnum.HighQuality;

		private enum StringEnum {
			MediumQuality,
			HighQuality
		}
	}

}
