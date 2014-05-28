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
package com.timeInc.dps.translator;

import java.io.StringReader;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONFunction;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.xml.JSONTypes;
import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Node;
import nu.xom.Text;

import org.apache.commons.lang.StringUtils;

/**
 * Converts a XML string to a JSON string. Attributes for an element are properties for a json. Nested elementes that are repeated in an xml
 * are considered as a JSON array.
 * 
 * 
 *  <?xml version="1.0" encoding="UTF-8"?>
	<results status="SUCCESS" message="Success">
	<requests>
	<request requestId="83a98e73-7b7e-47b5-8f48-48f68c2610e9" jobStatus="completed" magazineTitle="Create Folio test title" issueTitle="Create Folio test" adcIssueId="-6OA-Y1GmEiohphiycIrcg" state="private" productId="com.adobe.boshea.qa2" retail="false" createTime="Wed Jul 24 09:46:33 PDT 2013" startTime="Wed Jul 24 09:46:41 PDT 2013" endTime="1374709622947" jobTasksTotal="5" jobTasksCompleted="5" updateContents="true" onSaleDate="1374684453747"/>
	<request requestId="af594014-0bdd-4939-a376-d183276a0290" jobStatus="completed" magazineTitle="Create Folio test title" issueTitle="Create Folio test" adcIssueId="-6OA-Y1GmEiohphiycIrcg" issueGUID="051ed4d2-179e-4d83-8f50-608f57243a75" state="public" productId="com.adobe.boshea.qa2" retail="false" createTime="Wed Jul 24 09:47:02 PDT 2013" startTime="Wed Jul 24 10:48:17 PDT 2013" endTime="1374688097456" jobTasksTotal="0" jobTasksCompleted="0" updateContents="true" onSaleDate="1374684453747" parentRequestId="83a98e73-7b7e-47b5-8f48-48f68c2610e9"/>
	</requests>
	</results>
	
	is converted to 
	
	{
	    "status": "SUCCESS",
	    "message": "Success",
	    "requests": [{
	        "requestId": "83a98e73-7b7e-47b5-8f48-48f68c2610e9",
	        "jobStatus": "completed",
	        "magazineTitle": "Create Folio test title",
	        "issueTitle": "Create Folio test",
	        "adcIssueId": "-6OA-Y1GmEiohphiycIrcg",
	        "state": "private",
	        "productId": "com.adobe.boshea.qa2",
	        "retail": "false",
	        "createTime": "Wed Jul 24 09:46:33 PDT 2013",
	        "startTime": "Wed Jul 24 09:46:41 PDT 2013",
	        "endTime": "1374709622947",
	        "jobTasksTotal": "5",
	        "jobTasksCompleted": "5",
	        "updateContents": "true",
	        "onSaleDate": "1374684453747"
	    }, {
	        "requestId": "af594014-0bdd-4939-a376-d183276a0290",
	        "jobStatus": "completed",
	        "magazineTitle": "Create Folio test title",
	        "issueTitle": "Create Folio test",
	        "adcIssueId": "-6OA-Y1GmEiohphiycIrcg",
	        "issueGUID": "051ed4d2-179e-4d83-8f50-608f57243a75",
	        "state": "public",
	        "productId": "com.adobe.boshea.qa2",
	        "retail": "false",
	        "createTime": "Wed Jul 24 09:47:02 PDT 2013",
	        "startTime": "Wed Jul 24 10:48:17 PDT 2013",
	        "endTime": "1374688097456",
	        "jobTasksTotal": "0",
	        "jobTasksCompleted": "0",
	        "updateContents": "true",
	        "onSaleDate": "1374684453747",
	        "parentRequestId": "83a98e73-7b7e-47b5-8f48-48f68c2610e9"
	    }]
	}
 * 
 * 
 */
public class XmlUtil {

	/**
	 * Converts xml string to json
	 *
	 * @param xml the xml string
	 * @return the json string
	 */
	public static String toJson(String xml) {
		try {
			return new XMLSerializer().read(xml).toString();
		} catch(Exception ex) {
			throw new IllegalArgumentException("XML is malformed", ex);
		}
	}

	
	public static void main(String... args) {
		System.out.println(XmlUtil.toJson("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<results status=\"SUCCESS\" message=\"Success\">\r\n<requests>\r\n<request requestId=\"83a98e73-7b7e-47b5-8f48-48f68c2610e9\" jobStatus=\"completed\" magazineTitle=\"Create Folio test title\" issueTitle=\"Create Folio test\" adcIssueId=\"-6OA-Y1GmEiohphiycIrcg\" state=\"private\" productId=\"com.adobe.boshea.qa2\" retail=\"false\" createTime=\"Wed Jul 24 09:46:33 PDT 2013\" startTime=\"Wed Jul 24 09:46:41 PDT 2013\" endTime=\"1374709622947\" jobTasksTotal=\"5\" jobTasksCompleted=\"5\" updateContents=\"true\" onSaleDate=\"1374684453747\"/>\r\n<request requestId=\"af594014-0bdd-4939-a376-d183276a0290\" jobStatus=\"completed\" magazineTitle=\"Create Folio test title\" issueTitle=\"Create Folio test\" adcIssueId=\"-6OA-Y1GmEiohphiycIrcg\" issueGUID=\"051ed4d2-179e-4d83-8f50-608f57243a75\" state=\"public\" productId=\"com.adobe.boshea.qa2\" retail=\"false\" createTime=\"Wed Jul 24 09:47:02 PDT 2013\" startTime=\"Wed Jul 24 10:48:17 PDT 2013\" endTime=\"1374688097456\" jobTasksTotal=\"0\" jobTasksCompleted=\"0\" updateContents=\"true\" onSaleDate=\"1374684453747\" parentRequestId=\"83a98e73-7b7e-47b5-8f48-48f68c2610e9\"/>\r\n</requests>\r\n</results>"));
	}
	
	/*
	 * Copyright 2002-2007 the original author or authors.
	 *
	 * Licensed under the Apache License, Version 2.0 (the "License");
	 * you may not use this file except in compliance with the License.
	 * You may obtain a copy of the License at
	 *
	 *      http://www.apache.org/licenses/LICENSE-2.0
	 *
	 * Unless required by applicable law or agreed to in writing, software
	 * distributed under the License is distributed on an "AS IS" BASIS,
	 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	 * See the License for the specific language governing permissions and
	 * limitations under the License.
	 * 
	 * 
	 * 
	 */
	private static class XMLSerializer {
		private static final String JSON_PREFIX = "json_";

		private boolean removeNamespacePrefixFromElements;
		private boolean skipNamespaces;
		private boolean trimSpaces;
		private boolean typeHintsCompatibility;
		private boolean typeHintsEnabled;


		public XMLSerializer() {
			setTypeHintsEnabled( true );
			setTypeHintsCompatibility( true );
			setSkipNamespaces( false );
			setRemoveNamespacePrefixFromElements( false );
			setTrimSpaces( false );
		}

		public void setTrimSpaces( boolean trimSpaces ) {
			this.trimSpaces = trimSpaces;
		}

		public void setTypeHintsEnabled( boolean typeHintsEnabled ) {
			this.typeHintsEnabled = typeHintsEnabled;
		}

		public void setRemoveNamespacePrefixFromElements( boolean removeNamespacePrefixFromElements ) {
			this.removeNamespacePrefixFromElements = removeNamespacePrefixFromElements;
		}

		public void setSkipNamespaces( boolean skipNamespaces ) {
			this.skipNamespaces = skipNamespaces;
		}

		public void setTypeHintsCompatibility( boolean typeHintsCompatibility ) {
			this.typeHintsCompatibility = typeHintsCompatibility;
		}

		public JSON read( String xml ) {
			JSON json = null;
			try{
				Document doc = new Builder().build( new StringReader( xml ) );
				Element root = doc.getRootElement();
				if( isNullObject( root ) ){
					return JSONNull.getInstance();
				}
				String defaultType = getType( root, JSONTypes.STRING );
				if( isArray( root, true ) ){
					json = processArrayElement( root, defaultType );
				}else{
					json = processObjectElement( root, defaultType );
				}
			}catch( JSONException jsone ){
				throw jsone;
			}catch( Exception e ){
				throw new JSONException( e );
			}
			return json;
		}

		private boolean isNullObject( Element element ) {
			if( element.getChildCount() == 0 ){
				if( element.getAttributeCount() == 0 ){
					return true;
				}else if( element.getAttribute( addJsonPrefix( "null" ) ) != null ){
					return true;
				}else if( element.getAttributeCount() == 1
						&& (element.getAttribute( addJsonPrefix( "class" ) ) != null || element.getAttribute( addJsonPrefix( "type" ) ) != null) ){
					return true;
				}else if( element.getAttributeCount() == 2
						&& (element.getAttribute( addJsonPrefix( "class" ) ) != null && element.getAttribute( addJsonPrefix( "type" ) ) != null) ){
					return true;
				}
			}
			return false;
		}

		private String addJsonPrefix( String str ) {
			if( !isTypeHintsCompatibility() ){
				return JSON_PREFIX + str;
			}
			return str;
		}

		/**
		 * Returns true if types hints will have a 'json_' prefix or not.
		 */
		public boolean isTypeHintsCompatibility() {
			return typeHintsCompatibility;
		}

		private String getType( Element element ) {
			return getType( element, null );
		}

		private String getType( Element element, String defaultType ) {
			Attribute attribute = element.getAttribute( addJsonPrefix( "type" ) );
			String type = null;
			if( attribute != null ){
				String typeText = attribute.getValue()
						.trim();
				if( JSONTypes.BOOLEAN.compareToIgnoreCase( typeText ) == 0 ){
					type = JSONTypes.BOOLEAN;
				}else if( JSONTypes.NUMBER.compareToIgnoreCase( typeText ) == 0 ){
					type = JSONTypes.NUMBER;
				}else if( JSONTypes.INTEGER.compareToIgnoreCase( typeText ) == 0 ){
					type = JSONTypes.INTEGER;
				}else if( JSONTypes.FLOAT.compareToIgnoreCase( typeText ) == 0 ){
					type = JSONTypes.FLOAT;
				}else if( JSONTypes.OBJECT.compareToIgnoreCase( typeText ) == 0 ){
					type = JSONTypes.OBJECT;
				}else if( JSONTypes.ARRAY.compareToIgnoreCase( typeText ) == 0 ){
					type = JSONTypes.ARRAY;
				}else if( JSONTypes.STRING.compareToIgnoreCase( typeText ) == 0 ){
					type = JSONTypes.STRING;
				}else if( JSONTypes.FUNCTION.compareToIgnoreCase( typeText ) == 0 ){
					type = JSONTypes.FUNCTION;
				}
			}else{
				if( defaultType != null ){
					type = defaultType;
				}
			}
			return type;
		}

		private JSON processArrayElement( Element element, String defaultType ) {
			JSONArray jsonArray = new JSONArray();
			// process children (including text)
			int childCount = element.getChildCount();
			for( int i = 0; i < childCount; i++ ){
				Node child = element.getChild( i );
				if( child instanceof Text ){
					Text text = (Text) child;
					if( StringUtils.isNotBlank( StringUtils.strip( text.getValue() ) ) ){
						jsonArray.element( text.getValue() );
					}
				}else if( child instanceof Element ){
					setValue( jsonArray, (Element) child, defaultType );
				}
			}
			return jsonArray;
		}

		private JSON processObjectElement( Element element, String defaultType ) {
			if( isNullObject( element ) ){
				return JSONNull.getInstance();
			}
			JSONObject jsonObject = new JSONObject();

			if( !skipNamespaces ){
				for( int j = 0; j < element.getNamespaceDeclarationCount(); j++ ){
					String prefix = element.getNamespacePrefix( j );
					String uri = element.getNamespaceURI( prefix );
					if( StringUtils.isBlank( uri ) ){
						continue;
					}
					if( !StringUtils.isBlank( prefix ) ){
						prefix = ":" + prefix;
					}
					setOrAccumulate( jsonObject, "@xmlns" + prefix, trimSpaceFromValue( uri ) );
				}
			}

			// process attributes first
			int attrCount = element.getAttributeCount();
			for( int i = 0; i < attrCount; i++ ){
				Attribute attr = element.getAttribute( i );
				String attrname = attr.getQualifiedName();
				if( isTypeHintsEnabled()
						&& (addJsonPrefix( "class" ).compareToIgnoreCase( attrname ) == 0 || addJsonPrefix(
								"type" ).compareToIgnoreCase( attrname ) == 0) ){
					continue;
				}
				String attrvalue = attr.getValue();
				setOrAccumulate( jsonObject, removeNamespacePrefix( attrname ),
						trimSpaceFromValue( attrvalue ) );
			}

			// process children (including text)
			int childCount = element.getChildCount();
			for( int i = 0; i < childCount; i++ ){
				Node child = element.getChild( i );
				if( child instanceof Text ){
					Text text = (Text) child;
					if( StringUtils.isNotBlank( StringUtils.strip( text.getValue() ) ) ){
						setOrAccumulate( jsonObject, "#text", trimSpaceFromValue( text.getValue() ) );
					}
				}else if( child instanceof Element ){
					setValue( jsonObject, (Element) child, defaultType );
				}
			}

			return jsonObject;
		}

		private String removeNamespacePrefix( String name ) {
			if( isRemoveNamespacePrefixFromElements() ){
				int colon = name.indexOf( ':' );
				return colon != -1 ? name.substring( colon + 1 ) : name;
			}
			return name;
		}

		public boolean isRemoveNamespacePrefixFromElements() {
			return removeNamespacePrefixFromElements;
		}

		private String trimSpaceFromValue( String value ) {
			if( isTrimSpaces() ){
				return value.trim();
			}
			return value;
		}

		public boolean isTrimSpaces() {
			return trimSpaces;
		}

		public boolean isTypeHintsEnabled() {
			return typeHintsEnabled;
		}

		private void setValue( JSONArray jsonArray, Element element, String defaultType ) {
			String clazz = getClass( element );
			String type = getType( element );
			type = (type == null) ? defaultType : type;

			if( hasNamespaces( element ) && !skipNamespaces ){
				jsonArray.element( simplifyValue( null, processElement( element, type ) ) );
				return;
			}else if( element.getAttributeCount() > 0 ){
				if( isFunction( element ) ){
					Attribute paramsAttribute = element.getAttribute( addJsonPrefix( "params" ) );
					String[] params = null;
					String text = element.getValue();
					params = StringUtils.split( paramsAttribute.getValue(), "," );
					jsonArray.element( new JSONFunction( params, text ) );
					return;
				}else{
					jsonArray.element( simplifyValue( null, processElement( element, type ) ) );
					return;
				}
			}

			boolean classProcessed = false;
			if( clazz != null ){
				if( clazz.compareToIgnoreCase( JSONTypes.ARRAY ) == 0 ){
					jsonArray.element( processArrayElement( element, type ) );
					classProcessed = true;
				}else if( clazz.compareToIgnoreCase( JSONTypes.OBJECT ) == 0 ){
					jsonArray.element( simplifyValue( null, processObjectElement( element, type ) ) );
					classProcessed = true;
				}
			}
			if( !classProcessed ){
				if( type.compareToIgnoreCase( JSONTypes.BOOLEAN ) == 0 ){
					jsonArray.element( Boolean.valueOf( element.getValue() ) );
				}else if( type.compareToIgnoreCase( JSONTypes.NUMBER ) == 0 ){
					// try integer first
					try{
						jsonArray.element( Integer.valueOf( element.getValue() ) );
					}catch( NumberFormatException e ){
						jsonArray.element( Double.valueOf( element.getValue() ) );
					}
				}else if( type.compareToIgnoreCase( JSONTypes.INTEGER ) == 0 ){
					jsonArray.element( Integer.valueOf( element.getValue() ) );
				}else if( type.compareToIgnoreCase( JSONTypes.FLOAT ) == 0 ){
					jsonArray.element( Double.valueOf( element.getValue() ) );
				}else if( type.compareToIgnoreCase( JSONTypes.FUNCTION ) == 0 ){
					String[] params = null;
					String text = element.getValue();
					Attribute paramsAttribute = element.getAttribute( addJsonPrefix( "params" ) );
					if( paramsAttribute != null ){
						params = StringUtils.split( paramsAttribute.getValue(), "," );
					}
					jsonArray.element( new JSONFunction( params, text ) );
				}else if( type.compareToIgnoreCase( JSONTypes.STRING ) == 0 ){
					// see if by any chance has a 'params' attribute
					Attribute paramsAttribute = element.getAttribute( addJsonPrefix( "params" ) );
					if( paramsAttribute != null ){
						String[] params = null;
						String text = element.getValue();
						params = StringUtils.split( paramsAttribute.getValue(), "," );
						jsonArray.element( new JSONFunction( params, text ) );
					}else{
						if( isArray( element, false ) ){
							jsonArray.element( processArrayElement( element, defaultType ) );
						}else if( isObject( element, false ) ){
							jsonArray.element( simplifyValue( null, processObjectElement( element,
									defaultType ) ) );
						}else{
							jsonArray.element( trimSpaceFromValue( element.getValue() ) );
						}
					}
				}
			}
		}


		private void setOrAccumulate( JSONObject jsonObject, String key, Object value ) {
			if( jsonObject.has( key ) ){
				jsonObject.accumulate( key, value );
				Object val = jsonObject.get( key );
				if( val instanceof JSONArray ){
					((JSONArray) val).setExpandElements( true );
				}
			}else{
				jsonObject.element( key, value );
			}
		}

		private boolean hasNamespaces( Element element ) {
			int namespaces = 0;
			for( int i = 0; i < element.getNamespaceDeclarationCount(); i++ ){
				String prefix = element.getNamespacePrefix( i );
				String uri = element.getNamespaceURI( prefix );
				if( StringUtils.isBlank( uri ) ){
					continue;
				}
				namespaces++;
			}
			return namespaces > 0;
		}

		private Object simplifyValue( JSONObject parent, Object json ) {
			if( json instanceof JSONObject ){
				JSONObject object = (JSONObject) json;
				if( parent != null ){
					// remove all duplicated @xmlns from child
					for( Iterator entries = parent.entrySet()
							.iterator(); entries.hasNext(); ){
						Map.Entry entry = (Map.Entry) entries.next();
						String key = (String) entry.getKey();
						Object value = entry.getValue();
						if( key.startsWith( "@xmlns" ) && value.equals( object.opt( key ) ) ){
							object.remove( key );
						}
					}
				}
				if( object.size() == 1 && object.has( "#text" ) ){
					return object.get( "#text" );
				}
			}
			return json;
		}

		private boolean isArray( Element element, boolean isTopLevel ) {
			boolean isArray = false;
			String clazz = getClass( element );
			if( clazz != null && clazz.equals( JSONTypes.ARRAY ) ){
				isArray = true;
			}else if( element.getAttributeCount() == 0 ){
				isArray = checkChildElements( element, isTopLevel );
			}else if( element.getAttributeCount() == 1
					&& (element.getAttribute( addJsonPrefix( "class" ) ) != null || element.getAttribute( addJsonPrefix( "type" ) ) != null) ){
				isArray = checkChildElements( element, isTopLevel );
			}else if( element.getAttributeCount() == 2
					&& (element.getAttribute( addJsonPrefix( "class" ) ) != null && element.getAttribute( addJsonPrefix( "type" ) ) != null) ){
				isArray = checkChildElements( element, isTopLevel );
			}

			if( isArray ){
				// check namespace
				for( int j = 0; j < element.getNamespaceDeclarationCount(); j++ ){
					String prefix = element.getNamespacePrefix( j );
					String uri = element.getNamespaceURI( prefix );
					if( !StringUtils.isBlank( uri ) ){
						return false;
					}
				}
			}

			return isArray;
		}


		private String getClass( Element element ) {
			Attribute attribute = element.getAttribute( addJsonPrefix( "class" ) );
			String clazz = null;
			if( attribute != null ){
				String clazzText = attribute.getValue()
						.trim();
				if( JSONTypes.OBJECT.compareToIgnoreCase( clazzText ) == 0 ){
					clazz = JSONTypes.OBJECT;
				}else if( JSONTypes.ARRAY.compareToIgnoreCase( clazzText ) == 0 ){
					clazz = JSONTypes.ARRAY;
				}
			}
			return clazz;
		}

		private boolean checkChildElements( Element element, boolean isTopLevel ) {
			int childCount = element.getChildCount();
			Elements elements = element.getChildElements();
			int elementCount = elements.size();

			if( childCount == 1 && element.getChild( 0 ) instanceof Text ){
				return isTopLevel;
			}

			if( childCount == elementCount ){
				if( elementCount == 0 ){
					return true;
				}
				if( elementCount == 1 ){
					if( element.getChild( 0 ) instanceof Text ){
						return true;
					}else{
						return false;
					}
				}
			}

			if( childCount > elementCount ){
				for( int i = 0; i < childCount; i++ ){
					Node node = element.getChild( i );
					if( node instanceof Text ){
						Text text = (Text) node;
						if( StringUtils.isNotBlank( StringUtils.strip( text.getValue() ) ) ){
							return false;
						}
					}
				}
			}

			String childName = elements.get( 0 )
					.getQualifiedName();
			for( int i = 1; i < elementCount; i++ ){
				if( childName.compareTo( elements.get( i )
						.getQualifiedName() ) != 0 ){
					return false;
				}
			}

			return true;
		}

		private boolean isObject( Element element, boolean isTopLevel ) {
			boolean isObject = false;
			if( !isArray( element, isTopLevel ) && !isFunction( element ) ){
				if( hasNamespaces( element ) ){
					return true;
				}

				int childCount = element.getChildCount();
				if( childCount == 1 && element.getChild( 0 ) instanceof Text ){
					return isTopLevel;
				}

				isObject = true;
			}
			return isObject;
		}

		private boolean isFunction( Element element ) {
			int attrCount = element.getAttributeCount();
			if( attrCount > 0 ){
				Attribute typeAttr = element.getAttribute( addJsonPrefix( "type" ) );
				Attribute paramsAttr = element.getAttribute( addJsonPrefix( "params" ) );
				if( attrCount == 1 && paramsAttr != null ){
					return true;
				}
				if( attrCount == 2 && paramsAttr != null && typeAttr != null && (typeAttr.getValue()
						.compareToIgnoreCase( JSONTypes.STRING ) == 0 || typeAttr.getValue()
						.compareToIgnoreCase( JSONTypes.FUNCTION ) == 0) ){
					return true;
				}
			}
			return false;
		}

		private Object processElement( Element element, String type ) {
			if( isNullObject( element ) ){
				return JSONNull.getInstance();
			}
			if( isArray( element, false ) ){
				return processArrayElement( element, type );
			}else if( isObject( element, false ) ){
				return processObjectElement( element, type );
			}else{
				return trimSpaceFromValue( element.getValue() );
			}
		}

		private void setValue( JSONObject jsonObject, Element element, String defaultType ) {
			String clazz = getClass( element );
			String type = getType( element );
			type = (type == null) ? defaultType : type;

			String key = removeNamespacePrefix( element.getQualifiedName() );
			if( hasNamespaces( element ) && !skipNamespaces ){
				setOrAccumulate( jsonObject, key, simplifyValue( jsonObject,
						processElement( element, type ) ) );
				return;
			}else if( element.getAttributeCount() > 0 ){
				if( isFunction( element ) ){
					Attribute paramsAttribute = element.getAttribute( addJsonPrefix( "params" ) );
					String[] params = null;
					String text = element.getValue();
					params = StringUtils.split( paramsAttribute.getValue(), "," );
					setOrAccumulate( jsonObject, key, new JSONFunction( params, text ) );
					return;
				}else{
					setOrAccumulate( jsonObject, key, simplifyValue( jsonObject, processElement( element,
							type ) ) );
					return;
				}
			}

			boolean classProcessed = false;
			if( clazz != null ){
				if( clazz.compareToIgnoreCase( JSONTypes.ARRAY ) == 0 ){
					setOrAccumulate( jsonObject, key, processArrayElement( element, type ) );
					classProcessed = true;
				}else if( clazz.compareToIgnoreCase( JSONTypes.OBJECT ) == 0 ){
					setOrAccumulate( jsonObject, key, simplifyValue( jsonObject, processObjectElement(
							element, type ) ) );
					classProcessed = true;
				}
			}
			if( !classProcessed ){
				if( type.compareToIgnoreCase( JSONTypes.BOOLEAN ) == 0 ){
					setOrAccumulate( jsonObject, key, Boolean.valueOf( element.getValue() ) );
				}else if( type.compareToIgnoreCase( JSONTypes.NUMBER ) == 0 ){
					// try integer first
					try{
						setOrAccumulate( jsonObject, key, Integer.valueOf( element.getValue() ) );
					}catch( NumberFormatException e ){
						setOrAccumulate( jsonObject, key, Double.valueOf( element.getValue() ) );
					}
				}else if( type.compareToIgnoreCase( JSONTypes.INTEGER ) == 0 ){
					setOrAccumulate( jsonObject, key, Integer.valueOf( element.getValue() ) );
				}else if( type.compareToIgnoreCase( JSONTypes.FLOAT ) == 0 ){
					setOrAccumulate( jsonObject, key, Double.valueOf( element.getValue() ) );
				}else if( type.compareToIgnoreCase( JSONTypes.FUNCTION ) == 0 ){
					String[] params = null;
					String text = element.getValue();
					Attribute paramsAttribute = element.getAttribute( addJsonPrefix( "params" ) );
					if( paramsAttribute != null ){
						params = StringUtils.split( paramsAttribute.getValue(), "," );
					}
					setOrAccumulate( jsonObject, key, new JSONFunction( params, text ) );
				}else if( type.compareToIgnoreCase( JSONTypes.STRING ) == 0 ){
					// see if by any chance has a 'params' attribute
					Attribute paramsAttribute = element.getAttribute( addJsonPrefix( "params" ) );
					if( paramsAttribute != null ){
						String[] params = null;
						String text = element.getValue();
						params = StringUtils.split( paramsAttribute.getValue(), "," );
						setOrAccumulate( jsonObject, key, new JSONFunction( params, text ) );
					}else{
						if( isArray( element, false ) ){
							setOrAccumulate( jsonObject, key, processArrayElement( element, defaultType ) );
						}else if( isObject( element, false ) ){
							setOrAccumulate( jsonObject, key, simplifyValue( jsonObject,
									processObjectElement( element, defaultType ) ) );
						}else{
							setOrAccumulate( jsonObject, key, trimSpaceFromValue( element.getValue() ) );
						}
					}
				}
			}
		}


	}



}

