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
package com.timeInc.dps.httpcommons;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import com.timeInc.dps.translator.RequestTranslator;

/**
 * A simplified Http Multipart request in which the parameter and file is included in the body.
 */
public class HttpMultiPartRequest extends BaseHttpCommonsRequest {
	
	/** The Constant FILE_PARAM. */
	public static final String FILE_PARAM = "file";
	
	/** The Constant REQUEST_PARAM. */
	public static final String REQUEST_PARAM = "request";

	private File file;

	/**
	 * Instantiates a new http multi part request.
	 *
	 * @param translator the translator
	 * @param file the file
	 */
	public HttpMultiPartRequest(RequestTranslator translator, File file) {
		super(translator);
		this.file = file;
	}
	
	@Override
	protected HttpRequestBase getRequest(String path) {
		HttpPost post = new HttpPost(path);
		MultipartEntity entity = new MultipartEntity();
		addFileToMultipartEntity(entity);
		post.setEntity(entity);
		return post;
	}
	
	private void addFileToMultipartEntity(MultipartEntity entity) {
		ContentBody articleToUpload = new FileBody(file);
		entity.addPart(FILE_PARAM,articleToUpload);

	}
	
	@Override
	protected void addTranslatedRequest(HttpRequestBase request, String parameter) {
		HttpPost post = (HttpPost) request;
		
		MultipartEntity entity = new MultipartEntity(); 
		try {
			entity.addPart(REQUEST_PARAM,new StringBody(parameter,ENCODING)); // PARAMETER ORDER MATTERS OR IT WILL NOT WORK!!
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} 
		addFileToMultipartEntity(entity);
		post.setEntity(entity);
	}
}
