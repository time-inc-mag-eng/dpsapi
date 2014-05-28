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
package integration.com.timeInc.dps.producer.translator;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.timeInc.dps.producer.translator.GsonProducerFactory;
import com.timeInc.dps.producer.translator.ProducerDate;
import com.timeInc.dps.translator.JsonTranslator;
import com.timeInc.dps.translator.TranslatableRequest;
import com.timeInc.dps.translator.TranslatableResponse;


@RunWith(JMock.class)
public class GsonProducerFactoryTest {
	protected final Mockery context = new Mockery();

	private final ProducerDate folioDate = context.mock(ProducerDate.class);	
	
	private final JsonTranslator translator = new JsonTranslator(GsonProducerFactory.getInstance(folioDate));
	
	
	@Test
	public void usesFolioDateToConvertDateToExpectedJsonFormat() {
		context.checking(new Expectations() {{ 
			exactly(1).of(folioDate).getStringFor(with(any(Date.class))); 
			will(returnValue("DATE"));
		}}); 
		
		String translatedJson = translator.convertToString(new DateTest());
		
		assertJsonEquals(translatedJson,"{\"date\":\"DATE\"}");
	}
	
	@Test
	public void usesFolioDateToConvertJsonToExpectedDate() {
		context.checking(new Expectations() {{ 
			exactly(1).of(folioDate).getDateFor(with(any(String.class)));
			will(returnValue(new Date(0)));
		}}); 		
		
		String json = "{\"date\":\"DATE\"}";
		
		DateTest actualDateTest = translator.convertToObject(DateTest.class, json);
		
		assertThat(actualDateTest,equalTo(new DateTest()));
		
	}
	
	
	public static void assertJsonEquals(String actualJson, String expectedJson) {
		JsonParser jsonParser = new JsonParser();
		
		JsonElement expected = jsonParser.parse(actualJson);
		JsonElement actual = jsonParser.parse(expectedJson);
		
		assertThat(expected,equalTo(actual));
	}
	
	private static class DateTest implements TranslatableResponse, TranslatableRequest {
		Date date = new Date(0);
		
	    @Override
	    public boolean equals(Object obj) { return EqualsBuilder.reflectionEquals(this, obj); }
	    @Override
	    public int hashCode() { return HashCodeBuilder.reflectionHashCode(this); }	
	    @Override
	    public String toString() { return ToStringBuilder.reflectionToString(this); }
	}
	
}
