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

import java.util.List;

import com.timeInc.folio.parser.article.sidecar.ArticleAccess;

/**
 * Represents an article's metadata
 */
public class ArticleFolioMetaData {
	private final String targetViewer;
	
	private final String orientation;
	
	private final int width;
	
	private final int height;
	
	private final String id;
	
	private final List<ContentStack> contentStacks;
	
	private ArticleAccess access;
	
	/**
	 * Instantiates a new article folio meta deta.
	 *
	 * @param targetViewer the target viewer
	 * @param orientation the orientation
	 * @param width the width
	 * @param height the height
	 * @param id the id
	 * @param contentStacks the content stacks
	 */
	public ArticleFolioMetaData(String targetViewer, String orientation,
			int width, int height, String id, List<ContentStack> contentStacks) {
		super();
		this.targetViewer = targetViewer;
		this.orientation = orientation;
		this.width = width;
		this.height = height;
		this.id = id;
		this.contentStacks = contentStacks;
	}
	
	/**
	 * Gets the access.
	 *
	 * @return the access
	 */
	public ArticleAccess getAccess() {
		return access;
	}

	/**
	 * Sets the access.
	 *
	 * @param access the new access
	 */
	public void setAccess(ArticleAccess access) {
		this.access = access;
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
	 * Gets the content stacks.
	 *
	 * @return the content stacks
	 */
	public List<ContentStack> getContentStacks() {
		return contentStacks;
	}

	public static class ContentStack {
		
		/** The intents. */
		private final List<String> intents;
		
		/** The id. */
		private final String id;
		
		
		/**
		 * Instantiates a new content stack.
		 *
		 * @param intents the intents
		 * @param id the id
		 */
		public ContentStack(List<String> intents, String id) {
			super();
			this.intents = intents;
			this.id = id;
		}

		/**
		 * Gets the intents.
		 *
		 * @return the intents
		 */
		public List<String> getIntents() {
			return intents;
		}
		
		/**
		 * Gets the id.
		 *
		 * @return the id
		 */
		public String getId() {
			return id;
		}
	}
}
