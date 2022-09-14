package com.ecobank.srms.utils;


import com.ecobank.srms.exceptions.GenericException;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;


public class JSONUtils {

    private final static ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule()).setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    private final static Logger log = LoggerFactory.getLogger(JSONUtils.class);


    /**
     * Stringifies an object into a string
     * @param sourceObject: the object to stringify
     * @return the stringify object
     */
    public static <T> String stringifyObject(T sourceObject) {

        try {
            return objectMapper.writeValueAsString(sourceObject);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new GenericException(ResponseCodes.BAD_DATA, "Couldn't convert object to JSON", HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Converts stringify json object back to object
     * @param json: the json string
     * @param clazz: the object target class
     * @param <T>:  the class parameter
     * @return the converted object
     */
    public static <T> T toObject(String json, Class<T> clazz) {

        try {
            return objectMapper.readValue(json, clazz);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new GenericException(ResponseCodes.BAD_DATA, "Couldn't string to object payload", HttpStatus.BAD_REQUEST);
        }
    }


}

