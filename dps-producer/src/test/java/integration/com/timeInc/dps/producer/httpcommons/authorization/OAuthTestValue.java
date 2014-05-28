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

import java.util.HashMap;
import java.util.Map;

import oauth.signpost.http.HttpParameters;

public class OAuthTestValue {
	private OAuthTestValue() { }
	
	public static final String CONSUMER_SECRET = "kd94hf93k423kf44";
	public static final String TOKEN_SECRET = "pfkkdhi9sl3r4s00";
    
	public static final String CONSUMER_KEY = "dpf43f3p2l4k3l03";
    public static final String SIGNATURE_METHOD = "HMAC-SHA256";
    public static final String NONCE = "kllo9940pd9333jh";
    public static final String TIMESTAMP = "1191242096";
	private static final String PERCENT_ENCODED_SIGNATURE = "3lALGsqdoSLbRogWjqHLGFyqpcQfk7eYgsLVxC8HTA8%3D";//jzvONcQXX1qLyuztX%2FV6ewnZvj%2BnP34zmLN2kY3IEI4%3D";

    
    public static final HttpParameters OAUTH_PARAMS = new HttpParameters();
    public static final Map<String,String> AUTH_HEADER_MAP = new HashMap<String,String>();
    
    static {
        OAUTH_PARAMS.put("oauth_consumer_key", CONSUMER_KEY);
        OAUTH_PARAMS.put("oauth_signature_method", SIGNATURE_METHOD);
        OAUTH_PARAMS.put("oauth_timestamp", TIMESTAMP);
        OAUTH_PARAMS.put("oauth_nonce", NONCE);
        
        AUTH_HEADER_MAP.put("OAuth oauth_consumer_key", CONSUMER_KEY); 
        AUTH_HEADER_MAP.put("oauth_signature_method", SIGNATURE_METHOD);
        AUTH_HEADER_MAP.put("oauth_timestamp", TIMESTAMP);
        AUTH_HEADER_MAP.put("oauth_nonce", NONCE);
        AUTH_HEADER_MAP.put("oauth_signature",PERCENT_ENCODED_SIGNATURE);

    }
    
	
}
