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
package com.timeInc.dps.translator;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class ReflectionTest {	
	private static final Integer ANY_MODIFIER = null;

	@Test
	public void includesAllFields() {
		assertThat(getNumberOfFieldsFor(SimpleModifier.class),equalTo(9));
	}


	@Test
	public void includesAllFieldsExceptStatic() {
		assertThat(getNumberOfFieldsFor(SimpleModifier.class,Modifier.STATIC),equalTo(8));
	}
	
	
	@Test
	public void includesOnlyStringField() {
		assertThat(getNumberOfTypesFor(SimpleModifier.class,String.class),equalTo(2));
	}	
	

	private int getNumberOfFieldsFor(Class<?> clazz) {
		return getNumberOfFieldsFor(clazz,ANY_MODIFIER);
	}
	
	private int getNumberOfFieldsFor(Class<?> clazz, Integer excludedModifier) {
		int numFields = 0;

		for(Field field : getFieldFor(clazz)) {
			int fieldModifier = field.getModifiers();
			if(excludedModifier==ANY_MODIFIER || fieldModifier!=excludedModifier ) 
				numFields++;
		}

		return numFields;
	}
	
	private Field[] getFieldFor(Class<?> clazz) {
		return clazz.getDeclaredFields();
	}
	
	private int getNumberOfTypesFor(Class<?> clazz, Class<?> type) {
		int typeCounter = 0;
		
		for(Field field : getFieldFor(clazz)) {
			if(field.getType() == type)  
				typeCounter++;
		}
		return typeCounter;
	}
	

	private static class SimpleModifier { // class containing all modifiers for properties excluding the combination of these modifiers
		public int publicMod;
		private int privateMod;
		protected int protectedMod;
		static int staticMod;
		transient int transientMod;
		volatile int volatileMod;
		final int finalMod = 0;
		String str1;
		String str2;
	}


}
