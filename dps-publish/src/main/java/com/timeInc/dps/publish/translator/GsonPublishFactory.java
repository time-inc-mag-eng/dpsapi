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
package com.timeInc.dps.publish.translator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * A factory for creating {@code com.google.gson.Gson} which conforms correctly with the
 * (de)serialization of requests and responses received from the folio producer api.
 * Specifically, the toString representation of enum types.
 */
public class GsonPublishFactory {
	
	private static final Gson gson = new GsonBuilder()
									.registerTypeAdapterFactory(new EnumToStrinoTypeFactory())
									.create();
	
	/**
	 * Gets an instance of {@code com.google.gson.Gson}
	 *
	 * @return a {@code com.google.gson.Gson} object
	 */
	public static Gson getInstance() {
		return gson;
	}
	
	
	private static class EnumToStrinoTypeFactory implements TypeAdapterFactory {
		public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
			
			@SuppressWarnings("unchecked")
			Class<T> rawType = (Class<T>) type.getRawType();
			if (!rawType.isEnum()) {
				return null;
			}

			final Map<String, T> toStringRepresentation = new HashMap<String, T>();
			for (T constant : rawType.getEnumConstants()) {
				toStringRepresentation.put(constant.toString(), constant);
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
						String strRep = reader.nextString();
						
						T obj = toStringRepresentation.get(strRep);
						if(obj == null)
							throw new IllegalStateException("Can not convert " + strRep);
						else
							return obj;
					}
				}
			};
		}

	}
}
