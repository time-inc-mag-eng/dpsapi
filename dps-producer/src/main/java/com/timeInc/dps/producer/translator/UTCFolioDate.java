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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


/**
 * Converts a {@code java.util.Date} object to ISO8601 UTC format, vice versa.
 */
public class UTCFolioDate implements ProducerDate {
	private static final String format = "yyyy-MM-dd'T'kk:mm:ss";
	
	private static final ThreadLocal<SimpleDateFormat> dateFormat = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			return sdf;
		}
	};

	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.translator.ProducerDate#getDateFor(java.lang.String)
	 */
	@Override
	public Date getDateFor(String date) {
		try {
			return dateFormat.get().parse(date);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Date: " + date + " is not in format " + format);
		}
	}

	/* (non-Javadoc)
	 * @see com.timeInc.dps.producer.translator.ProducerDate#getStringFor(java.util.Date)
	 */
	@Override
	public String getStringFor(Date date) {
		return dateFormat.get().format(date);
	}
}
