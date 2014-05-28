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

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.timeInc.dps.producer.translator.UTCFolioDate;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class UTCFolioDateTest {
	
	private Date expectedDate = expectedDate();
	private String expectedDateString = "2010-06-22T14:53:10";
	private final UTCFolioDate folioDate = new UTCFolioDate();
	
	
	@Test
	public void convertsDateIntoExpectedFormat() {
		String actualDateString = folioDate.getStringFor(expectedDate);
		assertThat(actualDateString,equalTo(expectedDateString));
	}
	
	@Test
	public void convertsStringIntoExpectedDate() {
		Date actualDate = folioDate.getDateFor(expectedDateString);
		
		assertThat(actualDate,equalTo(expectedDate));
	}
	
	
	private static Date expectedDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,2010);
		cal.set(Calendar.MONTH,5); // month starts at 0
		cal.set(Calendar.DAY_OF_MONTH,22);
		cal.set(Calendar.HOUR_OF_DAY,10);
		cal.set(Calendar.MINUTE,53);
		cal.set(Calendar.SECOND,10);
		cal.clear(Calendar.MILLISECOND);
		
		return cal.getTime();
	}

}
