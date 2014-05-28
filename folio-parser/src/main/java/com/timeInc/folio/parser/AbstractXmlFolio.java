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
package com.timeInc.folio.parser;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.timeInc.folio.parser.util.AbstractXmlReader;

/**
 * A skeleton implementation for reading a folio/article xml meta data file. Since the structure  
 * in the master folio.xml and article's folio.xml are the same, this class extracts the common properties such as 
 * resolution and orientation.
 *
 * @param <T> the meta data that is extracted from the xml file
 */
public abstract class AbstractXmlFolio<T> extends AbstractXmlReader<T>  {
	
	/**
	 * Gets the root document.
	 *
	 * @return the root document
	 */
	protected Node getRootDocument() {
		return xPathReader.getDocument();
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	protected String getId() {
		return xPathReader.getStringLaunderException("/folio/@id", getRootDocument());
	}
	
	/**
	 * Gets the articles.
	 *
	 * @return the articles
	 */
	List<String> getArticles() {
		NodeList dosserIdNodes = getContentStackNode();

		List<String> ids = new ArrayList<String>();
		
		for(int i=0; i< dosserIdNodes.getLength(); i++) {
			Node node = dosserIdNodes.item(i);
			String dossierId = xPathReader.getStringLaunderException("@id",node);
			ids.add(dossierId);
		}
		return ids;
	}
	
	
	/**
	 * Gets the content stack node.
	 *
	 * @return the content stack node
	 */
	protected NodeList getContentStackNode() {
		NodeList contentStackNodes = xPathReader.getNodesLaunderException("//contentStacks/contentStack", getRootDocument() );
		return contentStackNodes;
	}
	

	/**
	 * Gets the orientation.
	 *
	 * @return the orientation
	 */
	protected String getOrientation() {
		return xPathReader.getStringLaunderException("/folio/@orientation", getRootDocument());
	}

	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	protected int getHeight() {
		return Integer.parseInt(xPathReader.getStringLaunderException("/folio/targetDimensions/targetDimension/@narrowDimension", getRootDocument()));	
	}


	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	protected int getWidth() {
		return Integer.parseInt(xPathReader.getStringLaunderException("/folio/targetDimensions/targetDimension/@wideDimension", getRootDocument()));	
	}
}
