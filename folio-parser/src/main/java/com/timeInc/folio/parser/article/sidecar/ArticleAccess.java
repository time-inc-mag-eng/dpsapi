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
package com.timeInc.folio.parser.article.sidecar;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an article's access level that is extracted from a generated
 * sidecar file
 */
public enum ArticleAccess {
	PROTECTED("protected"),  METERED("metered"), NONE("none"), 
	 FREE("free") {
		@Override
		public String getMinTargetVersion() {
			return "26";
		}
	};

	/**
	 * Gets the minimum version that is supported if this
	 * access level was to be used
	 *
	 * @return the min target version
	 */
	public String getMinTargetVersion() {
		return "";
	}

	
	private static final Map<String, ArticleAccess> strEnumMap = new HashMap<String, ArticleAccess>();

	static {
		for(ArticleAccess access : ArticleAccess.values()) {
			strEnumMap.put(access.strVal, access);
		}
	}

	private final String strVal;

	private ArticleAccess(String strVal) {
		this.strVal = strVal;
	}

	/**
	 * Converts a string access level to an an ArticleAccess
	 * type.
	 *
	 * @param strVal the access level.
	 * @return the ArticleAccess
	 */
	public static ArticleAccess getAccess(String strVal) {
		ArticleAccess access = strEnumMap.get(strVal);

		if(access == null) 
			throw new IllegalArgumentException("Unknown access string: " + strVal);
		else
			return access;
	}
}
