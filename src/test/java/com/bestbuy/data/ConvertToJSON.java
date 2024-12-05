package com.bestbuy.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ConvertToJSON {

    private final static ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    public static String the(Object objectInstance) {
        try {
            return objectMapper.writeValueAsString(objectInstance);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
