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

import com.timeInc.dps.producer.enums.AssetFormat;
import com.timeInc.dps.producer.enums.DownloadPriority;
import com.timeInc.dps.producer.enums.JPEGQuality;
import com.timeInc.dps.producer.enums.Orientation;
import com.timeInc.dps.producer.enums.ProtectedAccess;
import com.timeInc.dps.producer.enums.SmoothScrolling;

/**
 * The ArticleMetaData data structure. See Adobe DPS folio documentation for more information.
 */
public class ArticleMetaData {
	private ProtectedAccess access;
	private AssetFormat assetFormat;
	private String author;
	private String description;
	private boolean flatten;
	private boolean hasAudio;
	private boolean hasVideo;
	private boolean hideFromTOC;
	private boolean isAdvertisement;
	private JPEGQuality jpegQuality;
	private String kicker;
	private boolean locked;
	private int numberOfLandscapeAssets;
	private int numberOfPortraitAssets;
	private Orientation orientation;
	private DownloadPriority downloadPriority;
	private int resolutionHeight;
	private int resolutionWidth;
	private String section;
	private SmoothScrolling smoothScrolling;
	private int sortNumber;
	private String tags;
	private String targetViewer;
	private String title;
	private int uncompressedFolioSize;
	private String userData;
	private boolean canAccessReceipt;
	
	/**
	 * Gets the access.
	 *
	 * @return the access
	 */
	public ProtectedAccess getAccess() {
		return access;
	}
	
	/**
	 * Gets the asset format.
	 *
	 * @return the asset format
	 */
	public AssetFormat getAssetFormat() {
		return assetFormat;
	}
	
	/**
	 * Gets the author.
	 *
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Checks if is flatten.
	 *
	 * @return true, if is flatten
	 */
	public boolean isFlatten() {
		return flatten;
	}
	
	/**
	 * Checks if is checks for audio.
	 *
	 * @return true, if is checks for audio
	 */
	public boolean isHasAudio() {
		return hasAudio;
	}
	
	/**
	 * Checks if is checks for video.
	 *
	 * @return true, if is checks for video
	 */
	public boolean isHasVideo() {
		return hasVideo;
	}
	
	/**
	 * Checks if is hide from toc.
	 *
	 * @return true, if is hide from toc
	 */
	public boolean isHideFromTOC() {
		return hideFromTOC;
	}
	
	/**
	 * Checks if is advertisement.
	 *
	 * @return true, if is advertisement
	 */
	public boolean isAdvertisement() {
		return isAdvertisement;
	}
	
	/**
	 * Gets the jpeg quality.
	 *
	 * @return the jpeg quality
	 */
	public JPEGQuality getJpegQuality() {
		return jpegQuality;
	}
	
	/**
	 * Gets the kicker.
	 *
	 * @return the kicker
	 */
	public String getKicker() {
		return kicker;
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
	 * Gets the number of landscape assets.
	 *
	 * @return the number of landscape assets
	 */
	public int getNumberOfLandscapeAssets() {
		return numberOfLandscapeAssets;
	}
	
	/**
	 * Gets the number of portrait assets.
	 *
	 * @return the number of portrait assets
	 */
	public int getNumberOfPortraitAssets() {
		return numberOfPortraitAssets;
	}
	
	/**
	 * Gets the orientation.
	 *
	 * @return the orientation
	 */
	public Orientation getOrientation() {
		return orientation;
	}
		
	/**
	 * Gets the download priority.
	 *
	 * @return the download priority
	 */
	public DownloadPriority getDownloadPriority() {
		return downloadPriority;
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
	 * Gets the resolution width.
	 *
	 * @return the resolution width
	 */
	public int getResolutionWidth() {
		return resolutionWidth;
	}
	
	/**
	 * Gets the section.
	 *
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	
	/**
	 * Gets the smooth scrolling.
	 *
	 * @return the smooth scrolling
	 */
	public SmoothScrolling getSmoothScrolling() {
		return smoothScrolling;
	}
	
	/**
	 * Gets the sort number.
	 *
	 * @return the sort number
	 */
	public int getSortNumber() {
		return sortNumber;
	}
	
	/**
	 * Gets the tags.
	 *
	 * @return the tags
	 */
	public String getTags() {
		return tags;
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
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Gets the uncompressed folio size.
	 *
	 * @return the uncompressed folio size
	 */
	public int getUncompressedFolioSize() {
		return uncompressedFolioSize;
	}
	
	/**
	 * Gets the user data.
	 *
	 * @return the user data
	 */
	public String getUserData() {
		return userData;
	}
	
	/**
	 * Checks if is can access receipt.
	 *
	 * @return true, if is can access receipt
	 */
	public boolean isCanAccessReceipt() {
		return canAccessReceipt;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ArticleMetaData [access=" + access + ", assetFormat="
				+ assetFormat + ", author=" + author + ", description="
				+ description + ", flatten=" + flatten + ", hasAudio="
				+ hasAudio + ", hasVideo=" + hasVideo + ", hideFromTOC="
				+ hideFromTOC + ", isAdvertisement=" + isAdvertisement
				+ ", jpegQuality=" + jpegQuality + ", kicker=" + kicker
				+ ", locked=" + locked + ", numberOfLandscapeAssets="
				+ numberOfLandscapeAssets + ", numberOfPortraitAssets="
				+ numberOfPortraitAssets + ", orientation=" + orientation
				+ ", downloadPriority=" + downloadPriority
				+ ", resolutionHeight=" + resolutionHeight
				+ ", resolutionWidth=" + resolutionWidth + ", section="
				+ section + ", smoothScrolling=" + smoothScrolling
				+ ", sortNumber=" + sortNumber + ", tags=" + tags
				+ ", targetViewer=" + targetViewer + ", title=" + title
				+ ", uncompressedFolioSize=" + uncompressedFolioSize
				+ ", userData=" + userData + ", canAccessReceipt="
				+ canAccessReceipt + "]";
	}
	
	
	
}
