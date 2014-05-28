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

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * A factory for creating {@code com.google.gson.Gson} which conforms correctly with the
 * (de)serialization of requests and responses received from the folio producer api.
 * Specifically, it correctly (de)serailizes the dates and the toString representation of enum types are used.
 */
public class GsonProducerFactory {
	private GsonProducerFactory() {}

	/**
	 * Gets the single instance of GsonProducerFactory.
	 *
	 * @return single instance of GsonProducerFactory
	 */
	public static Gson getInstance() {
		return getInstance(new UTCFolioDate());
	}
	
	/**
	 * Gets the single instance of GsonProducerFactory.
	 *
	 * @param folioDate the folio date
	 * @return single instance of GsonProducerFactory
	 */
	public static Gson getInstance(ProducerDate folioDate) {
		return new GsonBuilder()
				.registerTypeAdapterFactory(new StringEnumTypeAdapterFactory())
				.registerTypeAdapter(Date.class,new FolioDateConverter(folioDate))
				.create();
	}
	
	private static class StringEnumTypeAdapterFactory implements TypeAdapterFactory {
		public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
			Class<T> rawType = (Class<T>) type.getRawType();
			
			if (!rawType.isEnum()) {
				return null;
			}

			final Map<String, T> stringToEnum = new HashMap<String, T>();
			for (T constant : rawType.getEnumConstants()) {
				stringToEnum.put(constant.toString(), constant);
			}

			return new TypeAdapter<T>() {
				public void write(JsonWriter out, T value) throws IOException {
					if (value == null) {
						out.nullValue();
					} else {
						out.value(value.toString());
					}
				}

				public T read(JsonReader reader) throws IOException {
					if (reader.peek() == JsonToken.NULL) {
						reader.nextNull();
						return null;
					} else {
						return stringToEnum.get(reader.nextString());
					}
				}
			};
		}
	}	
	

	private static class FolioDateConverter implements JsonDeserializer<Date>, JsonSerializer<Date> {
		private final ProducerDate folioDate;

		public FolioDateConverter(ProducerDate folioDate) {
			this.folioDate = folioDate;
		}		

		@Override
		public JsonElement serialize(Date date, java.lang.reflect.Type type, JsonSerializationContext context) {
			return new JsonPrimitive(folioDate.getStringFor(date));
		}

		@Override
		public Date deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
			if(element.isJsonNull() || element.getAsString().isEmpty())
				return null;

			return folioDate.getDateFor(element.getAsString());
		}	
	}
	
}
