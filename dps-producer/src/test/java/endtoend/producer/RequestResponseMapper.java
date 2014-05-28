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
//import com.timeInc.dps.translator.HttpParameterTranslator;
//import com.timeInc.dps.translator.JsonTranslator;
//import com.timeInc.dps.translator.RequestTranslator;
//import com.timeInc.dps.translator.TranslatableRequest;
//import com.timeInc.dps.translator.TranslatableResponse;
//
//class RequestResponseMapper<T extends TranslatableRequest,V extends TranslatableResponse> {
//	private static final RequestTranslator jsonTranslator = new JsonTranslator();
//	private static final RequestTranslator httpQueryTranslator = new HttpParameterTranslator();
//	
//	
//	private final T request;
//	private final V response;
//	
//	private RequestTranslator requestTranslator;
//
//
//	private RequestResponseMapper(T request, V response) {
//		this.request = request;
//		this.response = response;
//		
//		setRequestTranslator(jsonTranslator);
//	}
//	
//	public static <T extends TranslatableRequest,V extends TranslatableResponse> RequestResponseMapper<T,V> given(T request, V response) {
//		return new RequestResponseMapper<T,V>(request,response);
//	}
//	
//	public static <V extends TranslatableResponse> RequestResponseMapper<TranslatableRequest,V> given(V response) {
//		return new RequestResponseMapper<TranslatableRequest,V>(new EmptyRequest(),response);
//	}
//	
//	private void setRequestTranslator(RequestTranslator requestTranslator) {
//		this.requestTranslator = requestTranslator;
//	}
//	
//	public T getRequestObject() {
//		return request;
//	}
//	
//	public String getRequest() {
//		return requestTranslator.convertToString(request);
//	}
//	
//	public V getResponseObject() {
//		return response;
//	}
//	
//	public String getResponse() {
//		return jsonTranslator.convertToString(response);
//	}
//	
//	
//	private static class EmptyRequest implements TranslatableRequest {} 
//	
//}
