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

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The metadata for the folio itself.
 */
public class FolioMetaData {
	private static final String HTML_RESOURCE_FOLDER = "HTMLResources";	

	private final String orientation;
	
	private int width;
	
	private int height;
	
	private final List<String> contentStackId; // aka dossierids
	
	private final String id;
	
	/**
	 * Instantiates a new folio meta data.
	 *
	 * @param orientation the orientation
	 * @param width the width
	 * @param height the height
	 * @param dossierIds the dossier ids
	 * @param id the id
	 */
	public FolioMetaData(String orientation, int width, int height, List<String> dossierIds, String id) {
		this.orientation = orientation;
		this.width = width;
		this.height = height;
		this.contentStackId = dossierIds;
		this.id = id;
	}
	
	/**
	 * Gets the content stack ids.
	 *
	 * @return the content stack ids
	 */
	public List<String> getContentStackIds() {
		return contentStackId;
	}

	/**
	 * Gets the orientation.
	 *
	 * @return the orientation
	 */
	public String getOrientation() {
		return orientation;
	}

	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Gets the html resource folder name.
	 *
	 * @return the html resource folder name
	 */
	public String getHtmlResourceFolderName() {
		return HTML_RESOURCE_FOLDER;
	}
	
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) { return EqualsBuilder.reflectionEquals(this, obj); }
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() { return HashCodeBuilder.reflectionHashCode(this); }	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() { return ToStringBuilder.reflectionToString(this); }
	
}
