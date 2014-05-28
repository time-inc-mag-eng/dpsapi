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
package com.timeInc.dps.publish.http.request.binding;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.timeInc.dps.http.request.HttpRequestMapper.Method;
import com.timeInc.dps.publish.request.config.PublishConfig;
import com.timeInc.dps.publish.request.config.TicketConfig;
import com.timeInc.dps.publish.request.config.UnpublishConfig;

public class RequestBindingTest {
	private String DONT_CARE = null;
	
	@Test
	public void loginRequestIsMappedCorrectly() {
		LoginRequest request = new LoginRequest(DONT_CARE,null);
		assertThat(request.getRelativePath(),equalTo("signInWithCredentials"));
		assertThat(request.getMethod(),equalTo(Method.POST));
	}
	
	@Test
	public void publishRequestIsMappedCorrectly() {
		PublishConfig config = PublishConfig.getPrivateInstance("aFolioId_",DONT_CARE,DONT_CARE,false);
		PublishRequest request = new PublishRequest(DONT_CARE,config);
		
		assertThat(request.getRelativePath(),equalTo("folios/aFolioId*/publish"));
		assertThat(request.getMethod(),equalTo(Method.POST));
	}
	
	@Test
	public void publishStatusRequestIsMappedCorrectly() {
		TicketConfig config = new TicketConfig(DONT_CARE,"aRequestId");
		PublishStatusRequest request = new PublishStatusRequest(DONT_CARE,config);
		
		assertThat(request.getRelativePath(),equalTo("publishRequests/aRequestId"));
		assertThat(request.getMethod(),equalTo(Method.GET));
	}
	
	
	@Test
	public void cancelRequestIsMappedCorrectly() {
		TicketConfig config = new TicketConfig(DONT_CARE,"aRequestId");
		CancelRequest request = new CancelRequest(DONT_CARE,config);
		
		assertThat(request.getRelativePath(),equalTo("publishRequests/aRequestId/cancel"));
		assertThat(request.getMethod(),equalTo(Method.POST));
	}
	
	@Test
	public void unpublishFolioRequestIsMappedCorrectly() {
		UnpublishConfig config = new UnpublishConfig(DONT_CARE,"aFolioId_");
		UnpublishRequest request = new UnpublishRequest(DONT_CARE,config);
		
		assertThat(request.getRelativePath(),equalTo("folios/aFolioId*/unpublish"));
		assertThat(request.getMethod(),equalTo(Method.POST));
	}
}
