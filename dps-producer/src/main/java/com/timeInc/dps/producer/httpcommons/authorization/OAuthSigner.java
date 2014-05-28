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
package com.timeInc.dps.producer.httpcommons.authorization;


import org.apache.http.HttpRequest;

import oauth.signpost.OAuth;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.http.HttpParameters;
import oauth.signpost.signature.AuthorizationHeaderSigningStrategy;

/**
 * Signs the request using OAuth. This is used in the context of opening
 * a dps session. See Adobe DPS documentation for more information.
 */
public class OAuthSigner extends CommonsHttpOAuthConsumer implements HttpSigner {
	private static final long serialVersionUID = 1L;

	private final String nonce;
	private final String timestamp;
	
	
	/**
	 * Instantiates a new oauth signer.
	 *
	 * @param consumerKey the consumer key
	 * @param consumerSecret the consumer secret
	 */
	public OAuthSigner(String consumerKey, String consumerSecret) {
		super(consumerKey, consumerSecret);
		setSigningStrategy(new AuthorizationHeaderSigningStrategy());
		setMessageSigner(new HmacSha256MessageSigner());
		
		this.nonce = "";
		this.timestamp = "";
		
	}
	
	/**
	 * Instantiates a new oauth signer.
	 *
	 * @param consumerKey the consumer key
	 * @param consumerSecret the consumer secret
	 * @param nonce the nonce
	 * @param timestamp the timestamp
	 */
	public OAuthSigner(String consumerKey, String consumerSecret, String nonce, String timestamp) {
		super(consumerKey, consumerSecret);
		setSigningStrategy(new AuthorizationHeaderSigningStrategy());
		setMessageSigner(new HmacSha256MessageSigner());
		
		this.nonce = nonce;
		this.timestamp = timestamp;
	}
	
	@Override
    protected void completeOAuthParameters(HttpParameters out) {
        if (!timestamp.isEmpty()) {
            out.put(OAuth.OAUTH_TIMESTAMP, timestamp, true);
        }
        if (!nonce.isEmpty()) {
            out.put(OAuth.OAUTH_NONCE, nonce , true);
        }	
		
		super.completeOAuthParameters(out);
		
		/* Important: dps producer does not have oauth version.
		 * Removing this will also affect how the signature 
		 * is calculated.		
		 */
		out.remove(OAuth.OAUTH_VERSION); 
	}

	/* (non-Javadoc)
	 * @see oauth.signpost.AbstractOAuthConsumer#sign(oauth.signpost.http.HttpRequest)
	 */
	@Override
	public void sign(HttpRequest request) {
		try {
			super.sign(request);
		} catch (OAuthMessageSignerException e) {
			throw new AuthorizationException(e);
		} catch (OAuthExpectationFailedException e) {
			throw new AuthorizationException(e);
		} catch (OAuthCommunicationException e) {
			throw new AuthorizationException(e);
		}
	}
}
