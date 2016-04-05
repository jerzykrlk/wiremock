/*
 * Copyright (C) 2011 Thomas Akehurst
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.tomakehurst.wiremock.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Iterator;

import static com.github.tomakehurst.wiremock.common.Exceptions.throwUnchecked;

public final class Json {
	
	private Json() {}

    public static <T> T read(String json, Class<T> clazz) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
			mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
			return mapper.readValue(json, clazz);
		} catch (IOException ioe) {
			return throwUnchecked(ioe, clazz);
		}
	}
	
	public static <T> String write(T object) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		} catch (IOException ioe) {
            return throwUnchecked(ioe, String.class);
		}
	}

	public static byte[] toByteArray(Object object) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsBytes(object);
		} catch (IOException ioe) {
            return throwUnchecked(ioe, byte[].class);
		}
	}

	public static JsonNode node(String json) {
        return read(json, JsonNode.class);
    }

    public static int maxDeepSize(JsonNode one, JsonNode two) {
        return Math.max(deepSize(one), deepSize(two));
    }

    public static int deepSize(JsonNode node) {
        if (node == null) {
            return 0;
        }

        int acc = 0;
        if (node.isContainerNode()) {

            for (JsonNode child : node) {
                acc++;
                if (child.isContainerNode()) {
                    acc += deepSize(child);
                }
            }
        } else {
            acc++;
        }

        return acc;
    }

}
