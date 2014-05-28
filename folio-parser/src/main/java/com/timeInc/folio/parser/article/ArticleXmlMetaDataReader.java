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
package com.timeInc.folio.parser.article;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.timeInc.folio.parser.AbstractXmlFolio;
import com.timeInc.folio.parser.article.ArticleFolioMetaData.ContentStack;

/**
 * Reads an article's meta data that is in xml format.
 */
public class ArticleXmlMetaDataReader extends AbstractXmlFolio<ArticleFolioMetaData> {

	private String getTargetViewer() {
		String target =  xPathReader.getStringLaunderException("/folio/@targetViewer", getRootDocument());
		return target.isEmpty() ? null : target;
	}
	
	
	private List<ContentStack> getContentStackInArticle() {
		NodeList nodeList = super.getContentStackNode();
		
		List<ContentStack> list = new ArrayList<ContentStack>(nodeList.getLength());
		
		for(int i=0;i<nodeList.getLength();i++) {
			Node node = nodeList.item(i);
			
			
			String dossierId = xPathReader.getStringLaunderException("@id", node);
			
			NodeList intentList = xPathReader.getNodesLaunderException("//content/regions/region/metadata/intents[string-length(text()) > '0']", node);
			
			ContentStack contentStack = null;
			
			if(intentList.getLength() > 0) {
				List<String> intents = new ArrayList<String>();
				
				for(int j=0;j<intentList.getLength();j++) {
					intents.add(intentList.item(j).getNodeValue());
				}
				
				contentStack = new ContentStack(intents,dossierId);
				
			} else 
				contentStack = new ContentStack(Collections.<String>emptyList(),dossierId);
			
			list.add(contentStack);
		}
		
		return list;
	}

	@Override
	protected ArticleFolioMetaData getMetaDataAfterXmlLoaded() {
		return new ArticleFolioMetaData(getTargetViewer(),getOrientation(),getWidth(),getHeight(),getId(),getContentStackInArticle());
	}
}
