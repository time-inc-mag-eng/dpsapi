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
package com.timeInc.dps.producer.response;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.timeInc.dps.producer.enums.*;

/**
 * The FolioInfo data structure. See Adobe DPS folio documentation for more information.
 */
public class FolioInfo {
	private final String folioID;
	private final String folioName;
	private final String magazineTitle;
	private final String folioNumber;
	private final Date publicationDate;
	private final Date coverDate;
	private final String folioDescription;
	private final int resolutionWidth;
	private final int resolutionHeight;
	private final AssetFormat defaultAssetFormat;
	private final JPEGQuality defaultJPEGQuality;
	private final boolean bindingRight;
	private final int version;
	private final boolean locked;
	private final FolioIntent folioIntent;
	private final String targetViewer;


	private FolioInfo(String folioID, String name, String magazineTitle,
			String folioNumber, Date publicationDate, Date coverDate,
			String folioDescription, int resolutionWidth, int resolutionHeight,
			AssetFormat defaultAssetFormat, JPEGQuality defaultJPEGQuality,
			boolean bindingRight, int version, boolean locked,
			FolioIntent folioIntent, String targetViewer) {

		this.folioID = folioID;
		this.folioName = name;
		this.magazineTitle = magazineTitle;
		this.folioNumber = folioNumber;
		this.publicationDate = publicationDate;
		this.coverDate = coverDate;
		this.folioDescription = folioDescription;
		this.resolutionWidth = resolutionWidth;
		this.resolutionHeight = resolutionHeight;
		this.defaultAssetFormat = defaultAssetFormat;
		this.defaultJPEGQuality = defaultJPEGQuality;
		this.bindingRight = bindingRight;
		this.version = version;
		this.locked = locked;
		this.folioIntent = folioIntent;
		this.targetViewer = targetViewer;
	}


	/**
	 * Gets the folio id.
	 *
	 * @return the folio id
	 */
	public String getFolioID() {
		return folioID;
	}


	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return folioName;
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
	 * Gets the folio number.
	 *
	 * @return the folio number
	 */
	public String getFolioNumber() {
		return folioNumber;
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
	 * Gets the folio description.
	 *
	 * @return the folio description
	 */
	public String getFolioDescription() {
		return folioDescription;
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
	 * Checks if is binding right.
	 *
	 * @return true, if is binding right
	 */
	public boolean isBindingRight() {
		return bindingRight;
	}


	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}


	/**
	 * Checks if is locked.
	 *
	 * @return true, if is locked
	 */
	public boolean isLocked() {
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
	 * Gets the target viewer.
	 *
	 * @return the target viewer
	 */
	public String getTargetViewer() {
		return targetViewer;
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
		private String folioID;
		private String name;
		private String magazineTitle;
		private String folioNumber;
		private Date publicationDate;
		private Date coverDate;
		private String folioDescription;
		private int resolutionWidth;
		private int resolutionHeight;
		private AssetFormat defaultAssetFormat;
		private JPEGQuality defaultJPEGQuality;
		private boolean bindingRight;
		private int version;
		private boolean locked;
		private FolioIntent folioIntent;
		private String targetViewer;


		/**
		 * Instantiates a new builder.
		 *
		 * @param id the id
		 */
		public Builder(String id) {
			this.folioID = id;
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
		 * With folio id.
		 *
		 * @param id the id
		 * @return the builder
		 */
		public Builder withFolioID(String id) { this.folioID = id; return this; }
		
		/**
		 * With name.
		 *
		 * @param name the name
		 * @return the builder
		 */
		public Builder withName(String name) { this.name = name; return this; }
		
		/**
		 * With version.
		 *
		 * @param version the version
		 * @return the builder
		 */
		public Builder withVersion(int version) { this.version = version; return this; }
		
		/**
		 * With target viewer.
		 *
		 * @param targetViewer the target viewer
		 * @return the builder
		 */
		public Builder withTargetViewer(String targetViewer) { this.targetViewer = targetViewer; return this; }


		/**
		 * Builds the.
		 *
		 * @return the folio info
		 */
		public FolioInfo build() {
			return new FolioInfo(folioID,name,magazineTitle,folioNumber,publicationDate,coverDate,
					folioDescription,resolutionWidth,resolutionHeight,defaultAssetFormat,defaultJPEGQuality,bindingRight,
					version,locked,folioIntent,targetViewer);
		}
	}
}
