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

import com.timeInc.dps.producer.enums.Viewer;
import com.timeInc.dps.translator.TranslatableRequest;


/**
 * The upload folio request. See Adobe DPS folio documentation for more information.
 */
public class UpdateFolioConfig implements TranslatableRequest {
	private final transient String folioId;
	
	private final String folioName;
	private final String magazineTitle;
	private final String folioNumber;
	private final String folioDescription;
	private final Date publicationDate;
	private final Date coverDate;
	private final Boolean bindingRight;
	private final Boolean locked;
	private final String targetViewer;
	private final Viewer viewer;
	
	
	private UpdateFolioConfig(String folioName, String magazineTitle,
			String folioNumber, String folioDescription, Date publicationDate,
			Date coverDate, Boolean bindingRight, Boolean locked, String folioId,
			String targetViewer, Viewer viewer) {
		super();
		this.folioName = folioName;
		this.magazineTitle = magazineTitle;
		this.folioNumber = folioNumber;
		this.folioDescription = folioDescription;
		this.publicationDate = publicationDate;
		this.coverDate = coverDate;
		this.bindingRight = bindingRight;
		this.locked = locked;
		this.folioId = folioId;
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
	 * Gets the viewer.
	 *
	 * @return the viewer
	 */
	public Viewer getViewer() {
		return viewer;
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
	 * Gets the folio id.
	 *
	 * @return the folio id
	 */
	public String getFolioId() {
		return folioId;
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
	 * Gets the binding right.
	 *
	 * @return the binding right
	 */
	public Boolean getBindingRight() {
		return bindingRight;
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
		private String folioName;
		private String magazineTitle;
		private String folioNumber;
		private String folioDescription;
		private Date publicationDate;
		private Date coverDate;
		private Boolean bindingRight;
		private Boolean locked;
		private String folioId;
		private String targetViewer;
		private Viewer viewer;
		

		/**
		 * Instantiates a new builder.
		 *
		 * @param folioId the folio id
		 */
		public Builder(String folioId) {
			this.folioId = folioId;
		}
		
		/**
		 * With folio name.
		 *
		 * @param name the name
		 * @return the builder
		 */
		public Builder withFolioName(String name) { this.folioName = name; return this; }
		
		/**
		 * With magazine title.
		 *
		 * @param title the title
		 * @return the builder
		 */
		public Builder withMagazineTitle(String title) { this.magazineTitle = title; return this; }
		
		/**
		 * With folio number.
		 *
		 * @param number the number
		 * @return the builder
		 */
		public Builder withFolioNumber(String number) { this.folioNumber = number; return this; }
		
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
		 * With target viewer.
		 *
		 * @param targetViewer the target viewer
		 * @return the builder
		 */
		public Builder withTargetViewer(String targetViewer) { this.targetViewer = targetViewer; return this; }
		
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
		 * @return the update folio config
		 */
		public UpdateFolioConfig build() {
			return new UpdateFolioConfig(folioName,magazineTitle,folioNumber,folioDescription,
					publicationDate,coverDate,bindingRight,locked,folioId,targetViewer,viewer);
		}
	}
	

}
