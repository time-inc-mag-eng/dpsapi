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
package com.timeInc.dps.producer.translator;

import java.util.Date;

/**
 * Converts a {@code java.util.Date} to a String, vice versa.
 */
public interface ProducerDate {
	/**
	 * Convert a string date to a {@code java.util.Date}
	 * @param date the string version of the date
	 * @return {@code javautil.Date}
	 */
	Date getDateFor(String date);
	
	/**
	 * Convert a {@code java.util.Date} to a string representation
	 * @param date the date object to convert
	 * @return a string representation of the date
	 */
	String getStringFor(Date date);
}
