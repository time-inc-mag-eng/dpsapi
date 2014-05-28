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

import org.junit.Test;

import com.timeInc.dps.translator.TranslatableResponse;
import com.timeInc.dps.translator.XmlResponseTranslator;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class XmlResponseTranslatorTest {
	@Test
	public void correctlyConvertXmlToObject() {
		String xmlString = "<note> " +
				"<to>Tove</to> " +
				"<from>Jani</from>" +
				"<heading>Reminder</heading>" +
				"<body>Dont forget me this weekend!</body>" +
				"</note>";

		XmlResponseTranslator translator = new XmlResponseTranslator();

		Sample sample = translator.convertToObject(Sample.class,xmlString);

		assertThat(sample.to,equalTo("Tove"));
		assertThat(sample.from,equalTo("Jani"));
		assertThat(sample.heading,equalTo("Reminder"));
		assertThat(sample.body,equalTo("Dont forget me this weekend!"));


	}

	private static class Sample implements TranslatableResponse {
		String to;
		String from;
		String heading;
		String body;
	}



}

