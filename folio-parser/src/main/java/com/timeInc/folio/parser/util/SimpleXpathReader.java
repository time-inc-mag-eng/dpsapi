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
package com.timeInc.folio.parser.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.timeInc.folio.parser.exception.ParserException;

/**
 * Simple xml reader that is used to extract values of an xml file.
 */
public final class SimpleXpathReader {
	private XPath xPath;
	
	private Document document;
	
	/**
	 * Instantiates a new simple xpath reader.
	 *
	 * @param xmlFile the xml file
	 */
	public SimpleXpathReader(File xmlFile) {
		this();
		this.document = initDocument(xmlFile);
	}
	
	/**
	 * Instantiates a new simple xpath reader.
	 */
	public SimpleXpathReader() {
		XPathFactory xPathFactory = XPathFactory.newInstance();
		this.xPath = xPathFactory.newXPath();
	}
	
	
	/**
	 * Load xml.
	 *
	 * @param xmlFile the xml file
	 */
	public void loadXml(File xmlFile) {
		this.document = initDocument(xmlFile);
	}
	

	private Document initDocument(File file) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			return factory.newDocumentBuilder().parse(file);
		} catch (Exception e) {
			throw new ParserException(e);
		}
	}
	
	/**
	 * Gets the document object of the loaded xml.
	 *
	 * @return the document
	 */
	public Document getDocument() {
		if(document == null) 
			throw new IllegalStateException("Xml file not loaded use loadXml(File xmlFile)");
		
		return document;
	}
	
	/**
	 * Gets a Node element using the xpath expression starting from the 
	 * specified node
	 *
	 * @param xpathExpr the xpath expression
	 * @param node the node to start from
	 * @return the selected node
	 * @throws ParserException if there was a problem with the xpath expression
	 */
	public Node getNodeLaunderException(String xpathExpr, Node node) {
		try {
			return handleNullElement(Node.class, getNode(xpathExpr,node));
		} catch (XPathExpressionException e) {
			throw new ParserException(e);
		}
	}
	
	
	/**
	 * Gets a list of nodes using the xpath expression starting from the
	 * specified node.
	 *
	 * @param xpathExpr the xpath expression
	 * @param node the node to start from
	 * @return the nodes that match the xpath
	 * @throws ParserException if there was a problem with the xpath expression
	 */
	public NodeList getNodesLaunderException(String xpathExpr, Node node) {
		try {
			return handleNullElement(NodeList.class,getNodes(xpathExpr,node));
		} catch (XPathExpressionException e) {
			throw new ParserException(e);
		}
	}

	private NodeList getNodes(String xpathExpr, Node node) throws XPathExpressionException  {
		XPathExpression xPathExpression = xPath.compile(xpathExpr);
		return (NodeList) xPathExpression.evaluate(node,XPathConstants.NODESET);
	}
	
	private Node getNode(String xpathExpr, Node node) throws XPathExpressionException  {
		XPathExpression xPathExpression = xPath.compile(xpathExpr);
		return (Node) xPathExpression.evaluate(node, XPathConstants.NODE);
	}


	/**
	 * Gets the string value of the selected xpath expression
	 *
	 * @param xpathExpr the xpath expression
	 * @param node the node to start from
	 * @return the value of at that selected xpath
	 * @throws ParserException if the expression if malformed or does not evaluate to a value element
	 */
	public String getStringLaunderException(String xpathExpr, Node node) {
		try {
			return handleNullElement(String.class,getString(xpathExpr,node));
		} catch (XPathExpressionException e) {
			throw new ParserException(e);
		}
	}

	private String getString(String xpathExpr, Node node) throws XPathExpressionException {
		XPathExpression xPathExpression = xPath.compile(xpathExpr);
		return xPathExpression.evaluate(node);
	}
	
	private static <T> T handleNullElement(Class<T> cls, T obj) {
		if(obj == null)
			throw new ParserException("Can not find element in xml"); 
		else 
			return obj;
	}
}
