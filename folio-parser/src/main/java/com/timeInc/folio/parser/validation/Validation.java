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
package com.timeInc.folio.parser.validation;


/**
 * Validation message that is used as the return type for 
 * @see com.timeInc.folio.parser.validation.FolioValidator#validate(com.timeInc.folio.parser.FolioMetaData, java.io.File)
 */
public class Validation {
	
	private final boolean isValid;
	private final String errorMsg;
	
	
	/**
	 * Get a successful validation default message.
	 * @return the validation message
	 */
	public static Validation getValid() {
		return new Validation(true,"");
	}
	
	/**
	 * Get an unsuccesful validation message
	 *
	 * @param invalidMsg the reson why the folio is invalid
	 * @return the validation message
	 */
	public static Validation getInvalid(String invalidMsg) {
		return new Validation(false,invalidMsg);
	}
	
	private Validation(boolean isValid, String errorMsg) {
		this.errorMsg = errorMsg;
		this.isValid = isValid;
	}
	
	/**
	 *
	 * @return true, if the folio is valid
	 */
	public boolean isValid() {
		return isValid;
	}
	
	/**
	 * Gets the error msg.
	 *
	 * @return the error msg if it is invalid otherwise 
	 * an empty String
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
}
