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
package integration.com.timeInc.dps.producer.httpcommons.authorization;

import static integration.com.timeInc.dps.producer.httpcommons.authorization.OAuthTestValue.*;
 
import oauth.signpost.http.HttpParameters;
import oauth.signpost.http.HttpRequest;
import oauth.signpost.signature.OAuthMessageSigner;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.timeInc.dps.producer.httpcommons.authorization.HmacSha256MessageSigner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;


@RunWith(JMock.class)
public class HmacSha256MessageSignerTest {
	private final Mockery context = new Mockery();
	private final HttpRequest request = context.mock(HttpRequest.class); 

	
	@Test
	public void returnsCorrectSignatureMethod() {
		OAuthMessageSigner signer = new HmacSha256MessageSigner();
		assertThat("Signature method",signer.getSignatureMethod(),equalTo(SIGNATURE_METHOD));
	}


	@Test
	public void computesCorrectSignature() throws Exception {
		context.checking(new Expectations() {{ 
			allowing(request).getRequestUrl(); will(returnValue("https://dpsapi2.acrobat.com/webservices/sessions"));
			allowing(request).getMethod(); will(returnValue("POST"));
		}}); 
		
		
		OAuthMessageSigner signer = new HmacSha256MessageSigner();
		signer.setConsumerSecret(CONSUMER_SECRET);
		signer.setTokenSecret(TOKEN_SECRET);

		HttpParameters params = new HttpParameters();
		params.putAll(OAUTH_PARAMS); // request body is a json so it is not used


		assertEquals("gxmKuwRcNu3uvUifyilZLCCOfCpTzHSz0AVO9VbxMbc=", signer.sign(request, params));	
	}
}
