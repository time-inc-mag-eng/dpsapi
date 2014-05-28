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
//import com.timeInc.dps.http.request.HttpRequestMapper;
//import com.timeInc.dps.producer.RetryingHttpProducer;
//import com.timeInc.dps.producer.HttpProducer;
//import com.timeInc.dps.producer.http.request.binding.CloseSessionRequest;
//import com.timeInc.dps.producer.http.request.binding.CreateFolioRequest;
//import com.timeInc.dps.producer.http.request.binding.DeleteArticleRequest;
//import com.timeInc.dps.producer.http.request.binding.FolioInfoRequest;
//import com.timeInc.dps.producer.http.request.binding.NewServerRequest;
//import com.timeInc.dps.producer.http.request.binding.OpenSessionRequest;
//import com.timeInc.dps.producer.http.request.binding.UpdateFolioRequest;
//import com.timeInc.dps.producer.http.request.binding.UploadArticleRequest;
//import com.timeInc.dps.producer.http.request.binding.UploadPreviewRequest;
//import com.timeInc.dps.producer.httpcommons.request.CommonsHttpRequestFactoryHelper;
//import com.timeInc.dps.producer.httpcommons.request.SomeFactoryThatLinks;
//import com.timeInc.dps.producer.request.config.CloseSessionConfig;
//import com.timeInc.dps.producer.request.config.CreateFolioConfig;
//import com.timeInc.dps.producer.request.config.DeleteArticleConfig;
//import com.timeInc.dps.producer.request.config.FolioInfoConfig;
//import com.timeInc.dps.producer.request.config.OpenSessionConfig;
//import com.timeInc.dps.producer.request.config.UpdateFolioConfig;
//import com.timeInc.dps.producer.request.config.UploadArticleConfig;
//import com.timeInc.dps.producer.request.config.UploadAssetConfig;
//import com.timeInc.dps.producer.response.CreateFolio;
//import com.timeInc.dps.producer.response.GetFolio;
//import com.timeInc.dps.producer.response.OpenSession;
//import com.timeInc.dps.producer.response.Server;
//import com.timeInc.dps.producer.response.Status;
//import com.timeInc.dps.producer.response.UpdateFolio;
//import com.timeInc.dps.producer.response.UploadArticle;
//
//public class ApplicationRunner {
//	private String DONT_CARE_CONSUMER_KEY = "key";
//	private String DONT_CARE_CONSUMER_SECRET = "secret";
//
//	private HttpProducer folio;
//	private final SomeFactoryThatLinks factory = new CommonsHttpRequestFactoryHelper(DONT_CARE_CONSUMER_KEY,DONT_CARE_CONSUMER_SECRET);
//
//	public ApplicationRunner() throws Exception {
//		folio = new RetryingHttpProducer(factory);
//	}
//	
//	public OpenSession openSession(OpenSessionConfig config) throws Exception {
//		HttpRequestMapper<OpenSession> test = new OpenSessionRequest(FakeFolioServer.getAddress(),config);
//		return folio.sendRequest(test);
//	}
//	
//	public Status closeSession(String ticket, CloseSessionConfig config) throws Exception {
//		HttpRequestMapper<Status> test = new CloseSessionRequest(FakeFolioServer.getAddress(),ticket,config);
//		return folio.sendRequest(test);
//	}
//	
//	public CreateFolio createFolio(String ticket, CreateFolioConfig config) throws Exception {
//		HttpRequestMapper<CreateFolio> test = new CreateFolioRequest(FakeFolioServer.getAddress(),ticket,config);
//		return folio.sendRequest(test);
//	}
//
//	public UploadArticle uploadArticle(UploadArticleConfig config, String ticket) throws Exception {
//		HttpRequestMapper<UploadArticle> test = new UploadArticleRequest(FakeFolioServer.getAddress(),ticket,config);
//		return folio.sendRequest(test);
//	}	
//	
//	public Status uploadLandscapePreview(UploadAssetConfig config, String ticket) throws Exception {
//		HttpRequestMapper<Status> test = UploadPreviewRequest.forLandscape(FakeFolioServer.getAddress(),ticket,config);
//		return folio.sendRequest(test);
//	}
//	
//	public Status uploadPortraitPreview(UploadAssetConfig config, String ticket) throws Exception {
//		HttpRequestMapper<Status> test = UploadPreviewRequest.forPortrait(FakeFolioServer.getAddress(),ticket,config);
//		return folio.sendRequest(test);
//	}
//	
//	public Server newServer(String ticket) throws Exception {
//		HttpRequestMapper<Server> test = new NewServerRequest(FakeFolioServer.getAddress(),ticket);
//		return folio.sendRequest(test);
//	}
//	
//	public Status deleteArticle(DeleteArticleConfig config, String ticket) throws Exception {
//		HttpRequestMapper<Status> test = new DeleteArticleRequest(FakeFolioServer.getAddress(),ticket,config);
//		return folio.sendRequest(test);
//	}
//	
//	public UpdateFolio updateFolio(UpdateFolioConfig config, String ticket) throws Exception {
//		HttpRequestMapper<UpdateFolio> test = new UpdateFolioRequest(FakeFolioServer.getAddress(),ticket,config);
//		return folio.sendRequest(test);
//	}
//
//	public GetFolio getFolioInfo(FolioInfoConfig config, String ticket) throws Exception {
//		HttpRequestMapper<GetFolio> test = new FolioInfoRequest(FakeFolioServer.getAddress(),ticket,config);
//		return folio.sendRequest(test);
//	}
//}
