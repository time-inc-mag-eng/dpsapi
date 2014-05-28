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
package endtoend.producer;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import org.apache.commons.io.IOUtils;
import static org.hamcrest.Matchers.*;
import static java.util.concurrent.TimeUnit.*;
import static org.junit.Assert.*;
import com.sun.net.httpserver.*;

@SuppressWarnings("restriction")
public class FakeFolioServer {

	private static final String HOST_NAME = "127.0.0.1";
	private static final InetSocketAddress address = new InetSocketAddress(HOST_NAME,8888);


	private static final String WEB_SERVICES_PATH = "/webservices";
	public static final String ANY_STRING = "";

	private final HttpServer folioServer;
	private final ArrayBlockingQueue<String> responseMessage = new ArrayBlockingQueue<String>(1);
	private final SingleHttpHandler httpHandler = new SingleHttpHandler(responseMessage);


	public static String getAddress() {
		return "http://" + HOST_NAME + ":" + address.getPort(); // can't call address.getHostName() since it sometimes resolves to localhost; which matters in oauth calculations
	}

	public FakeFolioServer() throws IOException {
		folioServer = HttpServer.create(address,-1);
	}

	public void startServer() {
		folioServer.createContext("/",httpHandler);
		folioServer.start();
	}


	public void stopServer() {
		httpHandler.close();
		folioServer.stop(1);
	}

	public void hasReceivedOpenSessionRequest(String request) throws Exception {
		Map<String,String> authMap = new HashMap<String,String>();
		authMap.put("OAuth oauth_consumer_key",ANY_STRING);
		authMap.put("oauth_signature_method",ANY_STRING);
		authMap.put("oauth_timestamp", ANY_STRING);
		authMap.put("oauth_nonce", ANY_STRING);
		authMap.put("oauth_signature",ANY_STRING);

		httpHandler.receivesARequest("post").withRequestBody(request).withPath(WEB_SERVICES_PATH + "/sessions").withAuthHeader(authMap,false);
	}

	public void hasReceivedCloseSessionRequest(String ticket, String queryParam) throws Exception {
		httpHandler.receivesARequest("delete").withPath(WEB_SERVICES_PATH + "/sessions").withQueryParameter(queryParam);

		hasMatchingAdobeAuthTicketHeader(ticket);
	}

	public void hasRecievedCreateFolioRequest(String ticket, String request) throws Exception {
		httpHandler.receivesARequest("post").withPath(WEB_SERVICES_PATH + "/folios").withRequestBody(request);
		hasMatchingAdobeAuthTicketHeader(ticket);
	}

	public void hasReceivedAuthHeaderWithParams(Map<String,String> authHeaderMap) throws Exception {
		httpHandler.receivesARequest("any").withAuthHeader(authHeaderMap,true);
	}


	public void hasReceivedUploadArticleRequest(String ticket, String folioId) throws Exception {
		httpHandler.receivesARequest("post").withPath(WEB_SERVICES_PATH + "/folios/" + folioId + "/articles");
		hasMatchingAdobeAuthTicketHeader(ticket);	
	}

	public void hasReceivedUploadLandscapePreviewRequest(String ticket,	String folioId) throws Exception {
		httpHandler.receivesARequest("post").withPath(WEB_SERVICES_PATH + "/folios/" + folioId + "/previews/landscape");
		hasMatchingAdobeAuthTicketHeader(ticket);	
	}

	public void hasReceivedUploadPortraitPreviewRequest(String ticket, String folioId) throws Exception {
		httpHandler.receivesARequest("post").withPath(WEB_SERVICES_PATH + "/folios/" + folioId + "/previews/portrait");
		hasMatchingAdobeAuthTicketHeader(ticket);	
	}
	
	public void hasReceivedGetFolioInfoRequest(String ticket, String folioId) throws Exception {
		httpHandler.receivesARequest("get").withPath(WEB_SERVICES_PATH + "/folios/" + folioId);
		hasMatchingAdobeAuthTicketHeader(ticket);	
	}
	

	public void hasReceivedNewServerRequest(String ticket) throws Exception {
		httpHandler.receivesARequest("get").withPath(WEB_SERVICES_PATH + "/sessions");
		hasMatchingAdobeAuthTicketHeader(ticket);		
	}

	public void hasReceivedDeleteArticleRequest(String ticket, String folioId, String articleId) throws Exception {
		httpHandler.receivesARequest("delete").withPath(WEB_SERVICES_PATH + "/folios/" + folioId + "/articles/" + articleId);
	}
	
	public void hasReceivedUpdateFolioRequestRequest(String ticket, String folioId, String request) throws Exception {
		httpHandler.receivesARequest("post").withPath(WEB_SERVICES_PATH + "/folios/" + folioId).withRequestBody(request);
		hasMatchingAdobeAuthTicketHeader(ticket);	
	}
	

	private void hasMatchingAdobeAuthTicketHeader(String ticket) throws Exception{
		Map<String,String> authMap = new HashMap<String,String>();
		authMap.put("AdobeAuth ticket",ticket);

		httpHandler.withAuthHeader(authMap,true);
	}
	

	public void isGoingToRespondWith(String response) {
		responseMessage.add(response);
	}

	private static class SingleHttpHandler implements HttpHandler {
		private final ArrayBlockingQueue<HttpExchange> requests = new ArrayBlockingQueue<HttpExchange>(1);
		private final ArrayBlockingQueue<String> responseMessage;
		private HttpExchange exchange;


		public SingleHttpHandler(ArrayBlockingQueue<String> responseMessage) {
			this.responseMessage = responseMessage;
		}

		@Override
		public void handle(HttpExchange exchange) throws IOException { // this is invoked by a worker thread
			requests.add(exchange);


			try {
				String responseMsg = responseMessage.poll(5,SECONDS);

				byte responseMsgBytes[] = responseMsg.getBytes();

				exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,responseMsgBytes.length);
				exchange.getResponseBody().write(responseMsgBytes);

			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}


		public SingleHttpHandler receivesARequest(String httpMethod) throws Exception {
			exchange = requests.poll(5,SECONDS);
			assertThat("Request received",exchange,is(notNullValue()));


			if(!httpMethod.equals("any"))
				assertThat("Http method:",exchange.getRequestMethod(),equalToIgnoringCase(httpMethod));

			return this;
		}

		private SingleHttpHandler withRequestBody(String requestBody) throws Exception {
			String messageBody = IOUtils.toString(exchange.getRequestBody());
			assertThat("Request Body",messageBody,equalTo(requestBody));		
			return this;
		}	

		private SingleHttpHandler withQueryParameter(String queryParam) throws Exception {
			assertThat("Query parameter:",exchange.getRequestURI().getQuery(),equalTo(queryParam));			
			return this;
		}

		private SingleHttpHandler withPath(String path) throws Exception {
			assertThat("Request path:",exchange.getRequestURI().getPath(),equalTo(path));
			return this;
		}

		public SingleHttpHandler withAuthHeader(Map<String,String> authHeaderMap, boolean matchParamValue) throws Exception {
			Headers headers = exchange.getRequestHeaders();

			String actualAuthHeader = headers.getFirst("Authorization"); 

			assertThat(actualAuthHeader,not(isEmptyString()));

			Map<String,String> actualAuthHeaderMap = new HashMap<String,String>();
			String tokenizedAuthHeader[] = actualAuthHeader.split(",");

			for(String aKeyPair : tokenizedAuthHeader) {
				String key = aKeyPair.substring(0,aKeyPair.indexOf("=")).trim();
				String value = aKeyPair.substring(aKeyPair.indexOf("\"")+1,aKeyPair.length()-1);
				actualAuthHeaderMap.put(key, value);
			}


			for(Map.Entry<String,String> entry : authHeaderMap.entrySet()) {			
				String key = entry.getKey();
				String value = entry.getValue();

				assertThat("Actual header" + actualAuthHeader + " ---- Auth header param " + key + " does not exist",actualAuthHeaderMap.get(key),is(notNullValue()));

				if(matchParamValue)
					assertThat("Auth header value " + key + ":",actualAuthHeaderMap.get(key),equalTo(value));
			}

			return this;
		}

		public void close() {
			if(exchange != null)
				exchange.close();
		}

	}




}
