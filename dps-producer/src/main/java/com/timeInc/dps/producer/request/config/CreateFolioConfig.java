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
package com.timeInc.dps.producer.request.config;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.timeInc.dps.producer.enums.AssetFormat;
import com.timeInc.dps.producer.enums.FolioIntent;
import com.timeInc.dps.producer.enums.JPEGQuality;
import com.timeInc.dps.producer.enums.Viewer;
import com.timeInc.dps.translator.TranslatableRequest;

/**
 * The create folio request. See Adobe DPS folio documentation for more information.
 */
public class CreateFolioConfig implements TranslatableRequest {
	private final String folioName;
	private final String magazineTitle;
	private final String folioNumber;
	private final int resolutionWidth;
	private final int resolutionHeight;

	private final String folioDescription;
	private final Date publicationDate;
	private final Date coverDate;
	private final Boolean bindingRight;
	private final Boolean locked;
	
	private final FolioIntent folioIntent;
	private final AssetFormat defaultAssetFormat;
	private final JPEGQuality defaultJPEGQuality;
	
	private final String targetViewer;
	private final Viewer viewer;
	
	private CreateFolioConfig(String folioName, String magazineTitle, 
			String folioNumber, int resolutionWidth, int resolutionHeight, 
			String folioDescription, Date publicationDate, Date coverDate,
			AssetFormat defaultAssetFormat, JPEGQuality defaultJPEGQuality, Boolean bindingRight,
			Boolean locked, FolioIntent folioIntent, String targetViewer, Viewer viewer) {
		this.folioName = folioName;
		this.magazineTitle = magazineTitle;
		this.folioNumber = folioNumber;
		this.resolutionHeight = resolutionHeight;
		this.resolutionWidth = resolutionWidth;

		this.folioDescription = folioDescription;
		this.publicationDate = publicationDate;
		this.coverDate = coverDate;
		this.defaultAssetFormat = defaultAssetFormat;
		this.defaultJPEGQuality = defaultJPEGQuality;
		this.bindingRight = bindingRight;
		this.locked = locked;
		this.folioIntent = folioIntent;
		
		this.targetViewer = targetViewer;
		
		this.viewer = viewer;

	}

	/**
	 * Gets the folio name.
	 *
	 * @return the folio name
	 */
	public String getFolioName() {
		return folioName;
	}


	/**
	 * Gets the folio number.
	 *
	 * @return the folio number
	 */
	public String getFolioNumber() {
		return folioNumber;
	}


	/**
	 * Gets the resolution width.
	 *
	 * @return the resolution width
	 */
	public int getResolutionWidth() {
		return resolutionWidth;
	}


	/**
	 * Gets the resolution height.
	 *
	 * @return the resolution height
	 */
	public int getResolutionHeight() {
		return resolutionHeight;
	}


	/**
	 * Gets the folio description.
	 *
	 * @return the folio description
	 */
	public String getFolioDescription() {
		return folioDescription;
	}


	/**
	 * Gets the publication date.
	 *
	 * @return the publication date
	 */
	public Date getPublicationDate() {
		return publicationDate;
	}

	/**
	 * Gets the cover date.
	 *
	 * @return the cover date
	 */
	public Date getCoverDate() {
		return coverDate;
	}

	/**
	 * Gets the default asset format.
	 *
	 * @return the default asset format
	 */
	public AssetFormat getDefaultAssetFormat() {
		return defaultAssetFormat;
	}

	/**
	 * Gets the default jpeg quality.
	 *
	 * @return the default jpeg quality
	 */
	public JPEGQuality getDefaultJPEGQuality() {
		return defaultJPEGQuality;
	}

	/**
	 * Gets the target viewer.
	 *
	 * @return the target viewer
	 */
	public String getTargetViewer() {
		return targetViewer;
	}
	
	/**
	 * Checks if is binding right.
	 *
	 * @return the boolean
	 */
	public Boolean isBindingRight() {
		return bindingRight;
	}


	/**
	 * Checks if is locked.
	 *
	 * @return the boolean
	 */
	public Boolean isLocked() {
		return locked;
	}


	/**
	 * Gets the folio intent.
	 *
	 * @return the folio intent
	 */
	public FolioIntent getFolioIntent() {
		return folioIntent;
	}

	/**
	 * Gets the magazine title.
	 *
	 * @return the magazine title
	 */
	public String getMagazineTitle() {
		return magazineTitle;
	}
	
	/**
	 * Gets the viewer.
	 *
	 * @return the viewer
	 */
	public Viewer getViewer() {
		return viewer;
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
    
    
	/**
	 * The Class Builder.
	 */
	public static class Builder {
		private final String folioName;
		private final String magazineTitle;
		private final String folioNumber;
		private final int resolutionWidth;
		private final int resolutionHeight;


		private  String folioDescription;
		private  Date publicationDate;
		private  Date coverDate;
		private  Boolean bindingRight;
		private  Boolean locked;
		
		private FolioIntent folioIntent;
		private AssetFormat defaultAssetFormat;
		private JPEGQuality defaultJPEGQuality;
		
		private String targetViewer;
		
		private Viewer viewer;


		/**
		 * Instantiates a new builder.
		 *
		 * @param folioName the folio name
		 * @param magazineTitle the magazine title
		 * @param folioNumber the folio number
		 * @param resolutionWidth the resolution width
		 * @param resolutionHeight the resolution height
		 */
		public Builder(String folioName, String magazineTitle, String folioNumber, int resolutionWidth, int resolutionHeight) {
			this.folioName = folioName;
			this.magazineTitle = magazineTitle;
			this.folioNumber = folioNumber;
			this.resolutionWidth = resolutionWidth;
			this.resolutionHeight = resolutionHeight;
		}

		
		/**
		 * With folio description.
		 *
		 * @param description the description
		 * @return the builder
		 */
		public Builder withFolioDescription(String description) { this.folioDescription = description; return this; }
		
		/**
		 * With publication date.
		 *
		 * @param pubDate the pub date
		 * @return the builder
		 */
		public Builder withPublicationDate(Date pubDate) { this.publicationDate = pubDate; return this; }
		
		/**
		 * With cover date.
		 *
		 * @param coverDate the cover date
		 * @return the builder
		 */
		public Builder withCoverDate(Date coverDate) { this.coverDate = coverDate; return this; }
		
		/**
		 * With default asset format.
		 *
		 * @param assetFormat the asset format
		 * @return the builder
		 */
		public Builder withDefaultAssetFormat(AssetFormat assetFormat) { this.defaultAssetFormat = assetFormat; return this; }
		
		/**
		 * With default jpeg quality.
		 *
		 * @param defaultJPEGQuality the default jpeg quality
		 * @return the builder
		 */
		public Builder withDefaultJPEGQuality(JPEGQuality defaultJPEGQuality) { this.defaultJPEGQuality = defaultJPEGQuality; return this; }
		
		/**
		 * With binding right.
		 *
		 * @param bindingRight the binding right
		 * @return the builder
		 */
		public Builder withBindingRight(boolean bindingRight) { this.bindingRight = bindingRight; return this; }
		
		/**
		 * With locked.
		 *
		 * @param locked the locked
		 * @return the builder
		 */
		public Builder withLocked(boolean locked) { this.locked = locked; return this; }
		
		/**
		 * With folio intent.
		 *
		 * @param folioIntent the folio intent
		 * @return the builder
		 */
		public Builder withFolioIntent(FolioIntent folioIntent) { this.folioIntent = folioIntent; return this; }

		/**
		 * With target viewer.
		 *
		 * @param targetViewer the target viewer
		 * @return the builder
		 */
		public Builder withTargetViewer(String targetViewer) {	this.targetViewer = targetViewer; return this; }
		
		/**
		 * With viewer.
		 *
		 * @param viewer the viewer
		 * @return the builder
		 */
		public Builder withViewer(Viewer viewer) { this.viewer = viewer; return this; }
		 
		/**
		 * Builds the.
		 *
		 * @return the creates the folio config
		 */
		public CreateFolioConfig build() {
			return new CreateFolioConfig(folioName,magazineTitle,folioNumber,resolutionWidth,resolutionHeight,folioDescription,
					publicationDate,coverDate,defaultAssetFormat,defaultJPEGQuality,bindingRight,locked,folioIntent,targetViewer,viewer);
		}
	}

}
