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
//package producer;
//
//import static org.hamcrest.Matchers.equalTo;
//import static org.junit.Assert.assertThat;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.util.Date;
//import java.util.UUID;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.TemporaryFolder;
//
//import com.timeInc.dps.producer.request.config.CloseSessionConfig;
//import com.timeInc.dps.producer.request.config.CreateFolioConfig;
//import com.timeInc.dps.producer.request.config.DeleteArticleConfig;
//import com.timeInc.dps.producer.request.config.FolioInfoConfig;
//import com.timeInc.dps.producer.request.config.OpenSessionConfig;
//import com.timeInc.dps.producer.request.config.UpdateFolioConfig;
//import com.timeInc.dps.producer.request.config.UploadArticleConfig;
//import com.timeInc.dps.producer.request.config.UploadAssetConfig;
//import com.timeInc.dps.producer.response.Article;
//import com.timeInc.dps.producer.response.CreateFolio;
//import com.timeInc.dps.producer.response.FolioInfo;
//import com.timeInc.dps.producer.response.GetFolio;
//import com.timeInc.dps.producer.response.OpenSession;
//import com.timeInc.dps.producer.response.Server;
//import com.timeInc.dps.producer.response.Status;
//import com.timeInc.dps.producer.response.UpdateFolio;
//import com.timeInc.dps.producer.response.UploadArticle;
//import com.timeInc.dps.translator.TranslatableRequest;
//
//public class FolioEndToEndTest {
//
//	public static final String TICKET = "4151E85E4054F7A4BB7BFAF9CC3FB5385EA6D";
//
//	private static FakeFolioServer server;
//
//	private static final String FOLIO_ID = "abcd123";
//	
//	private static final String ARTICLE_ID = "article123";
//	
//	private static final String OK_STATUS = "ok";
//	
//	private static final Status STATUS_RESPONSE = new Status(OK_STATUS);
//	
//	private static final OpenSessionConfig OPEN_SESSION_REQUEST = new OpenSessionConfig("x@x.com","password",true);
//	private static final OpenSession OPEN_SESSION_RESPONSE = new OpenSession("authToken",OK_STATUS,
//			"ticket","downloadTicket","server","downloadServer",false,300);
//
//	private static final CreateFolioConfig CREATE_FOLIO_REQUEST = new CreateFolioConfig.Builder("folioName","magazineTitle","folioNumber",1000,2000).withPublicationDate(new Date()).build();
//	private static final CreateFolio CREATE_FOLIO_RESPONSE = new CreateFolio("myfolioid",OK_STATUS);
//
//	private static final UploadArticle UPLOAD_ARTICLE_RESPONSE = new UploadArticle(OK_STATUS,new Article("id","name","type"));
//
//	private static final Server NEW_SERVER_RESPONSE = new Server(OK_STATUS,"http://new.cluster.bleh","http://newdownloadserver.com");
//	
//	private static final CloseSessionConfig CLOSE_SESSION_REQUEST = new CloseSessionConfig();
//	
//	private static final DeleteArticleConfig DELETE_FOLIO_REQUEST = new DeleteArticleConfig(FOLIO_ID,ARTICLE_ID);
//	
//	
//
//	
//	private static final FolioInfoConfig FOLIO_INFO_REQUEST = new FolioInfoConfig(FOLIO_ID);
//	private static final FolioInfo FOLIO_INFO = new FolioInfo.Builder(FOLIO_ID).build();
//	private static final GetFolio GET_FOLIO_RESPONSE = new GetFolio(FOLIO_INFO,OK_STATUS);
//	
//	private static final UpdateFolioConfig UPDATE_FOLIO_REQUEST = new UpdateFolioConfig.Builder(FOLIO_ID).withFolioDescription("new description").build();
//	private static final UpdateFolio UPDATE_FOLIO_RESPONSE = new UpdateFolio(OK_STATUS,FOLIO_INFO);
//	
//
//	private ApplicationRunner app;	
//
//	@Rule
//	public TemporaryFolder folder = new TemporaryFolder();
//
//
//	@Before
//	public void start() throws Exception {
//		server = new FakeFolioServer();
//		server.startServer();
//		app = new ApplicationRunner();
//	}
//
//	@After
//	public void stopFolioServer() {
//		server.stopServer();
//	}
//
//	@Test
//	public void canOpenSession() throws Exception {
//		RequestResponseMapper<OpenSessionConfig,OpenSession> mapping = RequestResponseMapper.given(OPEN_SESSION_REQUEST, OPEN_SESSION_RESPONSE);
//
//		server.isGoingToRespondWith(mapping.getResponse());
//
//		OpenSession response = app.openSession(mapping.getRequestObject());	
//
//		server.hasReceivedOpenSessionRequest(mapping.getRequest());
//
//		assertThat("Client matches server response",response,equalTo(mapping.getResponseObject()));
//	}
//	
//	@Test
//	public void closeSession() throws Exception {
//		RequestResponseMapper<CloseSessionConfig,Status> mapping = RequestResponseMapper.given(CLOSE_SESSION_REQUEST,STATUS_RESPONSE);
//		
//		server.isGoingToRespondWith(mapping.getResponse());
//		
//		Status response = app.closeSession(TICKET,mapping.getRequestObject());
//		
//		server.hasReceivedCloseSessionRequest(TICKET,mapping.getRequest());
//		
//		assertThat("Client matches server response",response,equalTo(mapping.getResponseObject()));
//	}
//	
//	@Test
//	public void newServerInstance() throws Exception {
//		RequestResponseMapper<TranslatableRequest,Server> mapping = RequestResponseMapper.given(NEW_SERVER_RESPONSE);
//		
//		server.isGoingToRespondWith(mapping.getResponse());
//		
//		Server response = app.newServer(TICKET);
//		
//		server.hasReceivedNewServerRequest(TICKET);
//		
//		assertThat("Client matches server response",response,equalTo(mapping.getResponseObject()));
//	}
//	
//	
//	@Test
//	public void createAFolio() throws Exception {
//		RequestResponseMapper<CreateFolioConfig,CreateFolio> mapping = RequestResponseMapper.given(CREATE_FOLIO_REQUEST, CREATE_FOLIO_RESPONSE);
//		
//		server.isGoingToRespondWith(mapping.getResponse());
//
//		CreateFolio response = app.createFolio(TICKET,mapping.getRequestObject());
//
//		server.hasRecievedCreateFolioRequest(TICKET,mapping.getRequest());
//
//		assertThat("Client matches server response",response,equalTo(mapping.getResponseObject()));
//	}
//
//	@Test
//	public void uploadArticle() throws Exception {
//		UploadAssetConfig cfg1 = getUploadAsset(FOLIO_ID);
//		UploadArticleConfig config = new UploadArticleConfig(cfg1.getFolioId(),cfg1.getAsset(),300,"somename");
//		
//		RequestResponseMapper<UploadArticleConfig,UploadArticle> mapping = RequestResponseMapper.given(config, UPLOAD_ARTICLE_RESPONSE);
//
//		server.isGoingToRespondWith(mapping.getResponse());
//
//		UploadArticle response = app.uploadArticle(mapping.getRequestObject(),TICKET);
//
//		server.hasReceivedUploadArticleRequest(TICKET,FOLIO_ID);
//
//		assertThat("Matching server response",response,equalTo(mapping.getResponseObject()));	
//	}
//	
//	@Test
//	public void uploadALandscapePreview() throws Exception {
//		RequestResponseMapper<UploadAssetConfig,Status> mapping = RequestResponseMapper.given(getUploadAsset(FOLIO_ID),STATUS_RESPONSE);
//
//		server.isGoingToRespondWith(mapping.getResponse());
//		
//		Status response = app.uploadLandscapePreview(mapping.getRequestObject(),TICKET);
//		
//		server.hasReceivedUploadLandscapePreviewRequest(TICKET,FOLIO_ID);
//		
//		assertThat("Client matches server response",response,equalTo(mapping.getResponseObject()));
//	}
//	
//	@Test
//	public void uploadAPortraitPreview() throws Exception {
//		RequestResponseMapper<UploadAssetConfig,Status> mapping = RequestResponseMapper.given(getUploadAsset(FOLIO_ID),STATUS_RESPONSE);
//
//		server.isGoingToRespondWith(mapping.getResponse());
//		
//		Status response = app.uploadPortraitPreview(mapping.getRequestObject(),TICKET);
//		
//		server.hasReceivedUploadPortraitPreviewRequest(TICKET,FOLIO_ID);
//		
//		assertThat("Client matches server response",response,equalTo(mapping.getResponseObject()));		
//	}
//	
//	
//	@Test
//	public void deleteArticle() throws Exception {
//		RequestResponseMapper<DeleteArticleConfig,Status> mapping = RequestResponseMapper.given(DELETE_FOLIO_REQUEST,STATUS_RESPONSE);
//		
//		server.isGoingToRespondWith(mapping.getResponse());
//		
//		Status response = app.deleteArticle(mapping.getRequestObject(),TICKET);
//		
//		server.hasReceivedDeleteArticleRequest(TICKET,FOLIO_ID,ARTICLE_ID);
//		
//		assertThat("Client matches server response",response,equalTo(mapping.getResponseObject()));		
//	}
//	
//	@Test
//	public void updateFolio() throws Exception {
//		RequestResponseMapper<UpdateFolioConfig,UpdateFolio> mapping = RequestResponseMapper.given(UPDATE_FOLIO_REQUEST,UPDATE_FOLIO_RESPONSE);
//		
//		server.isGoingToRespondWith(mapping.getResponse());
//		
//		UpdateFolio response = app.updateFolio(mapping.getRequestObject(),TICKET);
//		
//		server.hasReceivedUpdateFolioRequestRequest(TICKET,FOLIO_ID,mapping.getRequest());
//		
//		assertThat("Client matches server response",response,equalTo(mapping.getResponseObject()));	
//	}
//	
//	@Test
//	public void getFolioInfo() throws Exception {
//		RequestResponseMapper<FolioInfoConfig,GetFolio> mapping = RequestResponseMapper.given(FOLIO_INFO_REQUEST,GET_FOLIO_RESPONSE);
//		
//		server.isGoingToRespondWith(mapping.getResponse());
//		
//		GetFolio response = app.getFolioInfo(mapping.getRequestObject(),TICKET);
//		
//		server.hasReceivedGetFolioInfoRequest(TICKET,FOLIO_ID);
//		
//		assertThat("Client matches server response",response,equalTo(mapping.getResponseObject()));
//		
//	}
//	
//	private UploadAssetConfig getUploadAsset(String folioId) throws Exception {
//		File articleFileToUpload = folder.newFile();
//		fillFileWithRandomData(articleFileToUpload);
//		UploadAssetConfig request = new UploadAssetConfig(folioId,articleFileToUpload);
//		return request;
//	}
//
//	private void fillFileWithRandomData(File file) throws Exception {
//		BufferedWriter out = new BufferedWriter(new FileWriter(file));
//		try {
//			for(int i=0;i<10;i++) {
//				out.write(UUID.randomUUID().toString());
//			} 
//		} finally {
//			out.close();
//		}
//	}
//}
