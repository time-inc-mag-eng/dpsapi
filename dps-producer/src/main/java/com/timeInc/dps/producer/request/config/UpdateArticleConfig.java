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

import com.timeInc.dps.producer.enums.DownloadPriority;
import com.timeInc.dps.producer.enums.ProtectedAccess;
import com.timeInc.dps.translator.TranslatableRequest;

/**
 * The update article request. See Adobe DPS folio documentation for more information.
 */
public class UpdateArticleConfig implements TranslatableRequest {

	private final transient String folioId;
	private final transient String articleId;
	
	private final String articleName;
	private final String author;
	private final Boolean canAccessReceipt;
	private final String description;
	private final DownloadPriority downloadPriority;
	private final Boolean hideFromTOC;
	private final Boolean isAdvertisement;
	private final String kicker;
	private final Boolean locked;
	private final String section;
	private final Integer sortOrder;
	private final String tags;
	private final String title;
	private final String userData;
	private final ProtectedAccess access;
	
	private UpdateArticleConfig(String folioId, String articleId,
			String articleName, String author, Boolean canAccessReceipt,
			String description, DownloadPriority downloadPriority,
			Boolean hideFromTOC, Boolean isAdvertisement, String kicker,
			Boolean locked, String section, Integer sortOrder, String tags,
			String title, String userData, ProtectedAccess access) {
		super();
		this.folioId = folioId;
		this.articleId = articleId;
		this.articleName = articleName;
		this.author = author;
		this.canAccessReceipt = canAccessReceipt;
		this.description = description;
		this.downloadPriority = downloadPriority;
		this.hideFromTOC = hideFromTOC;
		this.isAdvertisement = isAdvertisement;
		this.kicker = kicker;
		this.locked = locked;
		this.section = section;
		this.sortOrder = sortOrder;
		this.tags = tags;
		this.title = title;
		this.userData = userData;
		this.access = access;
	}


	/**
	 * Gets the folio id.
	 *
	 * @return the folio id
	 */
	public String getFolioId() {
		return folioId;
	}


	/**
	 * Gets the article id.
	 *
	 * @return the article id
	 */
	public String getArticleId() {
		return articleId;
	}


	/**
	 * Gets the article name.
	 *
	 * @return the article name
	 */
	public String getArticleName() {
		return articleName;
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
	 * Gets the can access receipt.
	 *
	 * @return the can access receipt
	 */
	public Boolean getCanAccessReceipt() {
		return canAccessReceipt;
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
	 * Gets the download priority.
	 *
	 * @return the download priority
	 */
	public DownloadPriority getDownloadPriority() {
		return downloadPriority;
	}


	/**
	 * Gets the hide from toc.
	 *
	 * @return the hide from toc
	 */
	public Boolean getHideFromTOC() {
		return hideFromTOC;
	}


	/**
	 * Gets the checks if is advertisement.
	 *
	 * @return the checks if is advertisement
	 */
	public Boolean getIsAdvertisement() {
		return isAdvertisement;
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
	 * Gets the locked.
	 *
	 * @return the locked
	 */
	public Boolean getLocked() {
		return locked;
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
	 * Gets the sort order.
	 *
	 * @return the sort order
	 */
	public Integer getSortOrder() {
		return sortOrder;
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
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
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
	 * Gets the access.
	 *
	 * @return the access
	 */
	public ProtectedAccess getAccess() {
		return access;
	}




	/**
	 * The Class Builder.
	 */
	public static class Builder {
		private final String folioId;
		private final String articleId;
		
		
		private String articleName;
		private String author;
		private Boolean canAccessReceipt;
		private String description;
		private DownloadPriority downloadPriority;
		private ProtectedAccess access;
		private Boolean hideFromTOC;
		private Boolean isAdvertisement;
		private String kicker;
		private Boolean locked;
		private String section;
		private Integer sortOrder;
		private String tags;
		private String title;
		private String userData;
		
		
		/**
		 * Instantiates a new builder.
		 *
		 * @param folioId the folio id
		 * @param articleId the article id
		 */
		public Builder(String folioId, String articleId) {
			this.folioId = folioId;
			this.articleId = articleId;
		}
		
		/**
		 * Builds the.
		 *
		 * @return the update article config
		 */
		public UpdateArticleConfig build() {
			return new UpdateArticleConfig(folioId, articleId, articleName,
					author, canAccessReceipt, description, downloadPriority,
					hideFromTOC, isAdvertisement, kicker, locked, section,
					sortOrder, tags, title, userData,access);
		}

		/**
		 * With article name.
		 *
		 * @param articleName the article name
		 * @return the builder
		 */
		public Builder withArticleName(String articleName) {
			this.articleName = articleName;
			return this;
			
		}
		
		/**
		 * With access.
		 *
		 * @param access the access
		 * @return the builder
		 */
		public Builder withAccess(ProtectedAccess access) {
			this.access = access;
			return this;
		}


		/**
		 * With author.
		 *
		 * @param author the author
		 * @return the builder
		 */
		public Builder withAuthor(String author) {
			this.author = author;
			return this;
		}


		/**
		 * With can access receipt.
		 *
		 * @param canAccessReceipt the can access receipt
		 * @return the builder
		 */
		public Builder withCanAccessReceipt(boolean canAccessReceipt) {
			this.canAccessReceipt = canAccessReceipt;
			return this;
		}


		/**
		 * With description.
		 *
		 * @param description the description
		 * @return the builder
		 */
		public Builder withDescription(String description) {
			this.description = description;
			return this;
		}


		/**
		 * With download priority.
		 *
		 * @param downloadPriority the download priority
		 * @return the builder
		 */
		public Builder withDownloadPriority(DownloadPriority downloadPriority) {
			this.downloadPriority = downloadPriority;
			return this;
		}


		/**
		 * With hide from toc.
		 *
		 * @param hideFromTOC the hide from toc
		 * @return the builder
		 */
		public Builder withHideFromTOC(boolean hideFromTOC) {
			this.hideFromTOC = hideFromTOC;
			return this;
		}


		/**
		 * With advertisement.
		 *
		 * @param isAdvertisement the is advertisement
		 * @return the builder
		 */
		public Builder withAdvertisement(boolean isAdvertisement) {
			this.isAdvertisement = isAdvertisement;
			return this;
		}


		/**
		 * With kicker.
		 *
		 * @param kicker the kicker
		 * @return the builder
		 */
		public Builder withKicker(String kicker) {
			this.kicker = kicker;
			return this;
		}


		/**
		 * With locked.
		 *
		 * @param locked the locked
		 * @return the builder
		 */
		public Builder withLocked(boolean locked) {
			this.locked = locked;
			return this;
		}


		/**
		 * With section.
		 *
		 * @param section the section
		 * @return the builder
		 */
		public Builder withSection(String section) {
			this.section = section;
			return this;
		}


		/**
		 * With sort order.
		 *
		 * @param sortOrder the sort order
		 * @return the builder
		 */
		public Builder withSortOrder(int sortOrder) {
			this.sortOrder = sortOrder;
			return this;
		}


		/**
		 * With tags.
		 *
		 * @param tags the tags
		 * @return the builder
		 */
		public Builder withTags(String tags) {
			this.tags = tags;
			return this;
		}


		/**
		 * With title.
		 *
		 * @param title the title
		 * @return the builder
		 */
		public Builder withTitle(String title) {
			this.title = title;
			return this;
		}


		/**
		 * With user data.
		 *
		 * @param userData the user data
		 * @return the builder
		 */
		public Builder withUserData(String userData) {
			this.userData = userData;
			return this;
		}
	}
}
