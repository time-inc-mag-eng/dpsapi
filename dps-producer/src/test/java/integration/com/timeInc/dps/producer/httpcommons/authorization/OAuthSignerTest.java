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

import static integration.com.timeInc.dps.producer.httpcommons.authorization.OAuthTestValue.AUTH_HEADER_MAP;
import static integration.com.timeInc.dps.producer.httpcommons.authorization.OAuthTestValue.CONSUMER_KEY;
import static integration.com.timeInc.dps.producer.httpcommons.authorization.OAuthTestValue.CONSUMER_SECRET;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.timeInc.dps.producer.httpcommons.authorization.OAuthSigner;

import endtoend.producer.FakeFolioServer;


public class OAuthSignerTest {
	private FakeFolioServer server;

	
	@Before
	public void start() throws Exception {
		server = new FakeFolioServer();
		server.startServer();
	}
	
	@After
	public void stop() throws Exception {
		server.stopServer();
	}

	@Test
	public void addsCorrectOAuthHeadersThatConformToFolio() throws Exception {
		server.isGoingToRespondWith(FakeFolioServer.ANY_STRING);
			
		sendOAuthRequest();

		server.hasReceivedAuthHeaderWithParams(AUTH_HEADER_MAP);
	}
	
	private void sendOAuthRequest() throws Exception {
		OAuthSigner consumer = new OAuthSigner(CONSUMER_KEY ,CONSUMER_SECRET, 
				AUTH_HEADER_MAP.get("oauth_nonce"), AUTH_HEADER_MAP.get("oauth_timestamp"));
		
		HttpPost request = new HttpPost(FakeFolioServer.getAddress());

		consumer.sign(request);

		HttpClient client = new DefaultHttpClient();

		client.execute(request);		
	}
}
