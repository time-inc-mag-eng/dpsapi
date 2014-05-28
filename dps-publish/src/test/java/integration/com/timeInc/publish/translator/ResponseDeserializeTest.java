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
package integration.com.timeInc.publish.translator;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.timeInc.dps.publish.response.Credential;
import com.timeInc.dps.publish.response.Publish;
import com.timeInc.dps.publish.response.PublishingStatus;
import com.timeInc.dps.publish.response.Result;
import com.timeInc.dps.publish.translator.GsonPublishFactory;
import com.timeInc.dps.translator.ResponseTranslator;
import com.timeInc.dps.translator.XmlResponseTranslator;


public class ResponseDeserializeTest {
	private final ResponseTranslator translator = new XmlResponseTranslator(GsonPublishFactory.getInstance());
	
	@Test
	public void correctlyDeserializesExpectedCredentialResponse() {
		String xml = "<results status=\"SUCCESS\" message=\"Success\"><ticket>ZGhzandxWj</ticket><accountId>614fb6903c4c4902ad1f6194477eb7b0</accountId></results>";
		Credential cred = translator.convertToObject(Credential.class,xml);
		
		assertThat(cred.getAccountId(),equalTo("614fb6903c4c4902ad1f6194477eb7b0"));
		assertThat(cred.getTicket(),equalTo("ZGhzandxWj"));
		assertThat(cred.getStatus(),equalTo("SUCCESS"));
		assertThat(cred.getMessage(),equalTo("Success"));
	}
	
	@Test
	public void correctlyDeserializesExpectedPublishingStatusResponse() {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<results status=\"SUCCESS\" message=\"Success\">\n<requests>\n<request requestId=\"4e35318f-0389-443f-876f-a3239de94e4a\" jobStatus=\"completed\" magazineTitle=\"basic test from FBP\" issueTitle=\"navToUI\" adcIssueId=\"VynVM8A4vMjN5Pm6rZlSYg\" issueState=\"test\" productId=\"com.abc.january2013\" brokers=\"appleStore\" createTime=\"Tue Nov 20 07:43:21 PST 2012\" startTime=\"Tue Nov 20 07:43:52 PST 2012\" endTime=\"1353455054359\" jobTasksTotal=\"8\" jobTasksCompleted=\"8\"/>\n</requests>\n</results>";
		PublishingStatus pubStatus = translator.convertToObject(PublishingStatus.class,xml);
		
		assertThat(pubStatus.getStatus(),equalTo("SUCCESS"));
		assertThat(pubStatus.getMessage(),equalTo("Success"));
		
		
		PublishingStatus.Response resp = pubStatus.getPublishStatus().get(0);
				
		assertThat(resp.getRequestId(),equalTo("4e35318f-0389-443f-876f-a3239de94e4a"));
		assertThat(resp.getJobStatus(),equalTo(PublishingStatus.Response.State.COMPLETED));
		assertThat(resp.getIssueTitle(),equalTo("navToUI"));
		assertThat(resp.getAdcIssueId(),equalTo("VynVM8A4vMjN5Pm6rZlSYg"));
		assertThat(resp.getProductId(),equalTo("com.abc.january2013"));
		assertThat(resp.getCreateTime(),equalTo("Tue Nov 20 07:43:21 PST 2012"));
		assertThat(resp.getStartTime(),equalTo("Tue Nov 20 07:43:52 PST 2012"));
		assertThat(resp.getEndTime(),equalTo(1353455054359L));
		assertThat(resp.getJobTasksTotal(),equalTo(8));
		assertThat(resp.getJobTasksCompleted(),equalTo(8));
		

	}
	
	@Test
	public void correctlyDeserializesExpectedPublishResponse() {
		String xml = "<results status=\"SUCCESS\" message=\"Success\"><requestId>551a28fd-10c3-42e5-aec4-485d6193ef35</requestId></results>";

		Publish pub = translator.convertToObject(Publish.class,xml);

		assertThat(pub.getRequestId(),equalTo("551a28fd-10c3-42e5-aec4-485d6193ef35"));
		assertThat(pub.getStatus(),equalTo("SUCCESS"));
		assertThat(pub.getMessage(),equalTo("Success"));
	}
	
	@Test
	public void correctlyDeserializesExpectedResultResponse() {
		String xml = "<results status=\"SUCCESS\" message=\"Success\"></results>";
		
		Result result = translator.convertToObject(Result.class,xml);
		
		assertThat(result.getStatus(),equalTo("SUCCESS"));
		assertThat(result.getMessage(),equalTo("Success"));
		
	}
	
	
	
}
