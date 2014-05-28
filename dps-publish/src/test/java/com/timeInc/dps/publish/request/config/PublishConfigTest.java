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
package com.timeInc.dps.publish.request.config;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.timeInc.dps.translator.TranslatableRequest;

public class PublishConfigTest {
	@Test
	public void stateIsPrivate() {
		PublishConfig config = PublishConfig.getPrivateInstance("folioId","ticket","productId",false);
		assertThat(config.getState(),equalTo(PublishConfig.State.PRIVATE));
	}
	
	@Test
	public void retailIsFalseWhenPublishIsPrivate() {
		PublishConfig config = PublishConfig.getPrivateInstance("folioId","ticket","productId",false);
		assertFalse(config.isRetail());
	}
	
	@Test
	public void stateIsPublic() {
		PublishConfig config = PublishConfig.getPublicInstance("folioId","ticket","productid",false,false);
		assertThat(config.getState(),equalTo(PublishConfig.State.PUBLIC));
	}
	
	@Test
	public void instanceOfTranslatableRequest() {
		assertTrue(TranslatableRequest.class.isAssignableFrom(PublishConfig.class));
	}
	
}
