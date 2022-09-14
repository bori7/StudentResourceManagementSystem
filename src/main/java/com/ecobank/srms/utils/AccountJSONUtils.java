package com.ecobank.srms.utils;


import com.ecobank.srms.encryption.RsaAesGcmStandard;
import com.ecobank.srms.exceptions.NrhException;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/* @author sa_oladipo created on 4/28/22 */



@Component
public class AccountJSONUtils {
    
    @Value("${payloadEncKey}")
    private String payloadEncKey;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule()).setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private final Logger log = LoggerFactory.getLogger(AccountJSONUtils.class);


    /**
     * Stringifies an object into a string
     * @param sourceObject: the object to stringify
     * @return the stringify object
     */
    public <T> String stringifyObject(T sourceObject) {

        try {
            return objectMapper.writeValueAsString(sourceObject);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new NrhException(ResponseEnum.INVALID_REQUEST, "Couldn't encrypt request");
        }
    }


    /**
     * Converts stringify json object back to object
     * @param json: the json string
     * @param clazz: the object target class
     * @param <T>:  the class parameter
     * @return the converted object
     */
    public <T> T toObject(String json, Class<T> clazz) {

        try {
            return objectMapper.readValue(json, clazz);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new NrhException(ResponseEnum.INVALID_REQUEST, "Couldn't decrypt response payload");
        }
    }

    /**
     * Encrypts an object and returns the string value
     * @param object: the object to encrypt
     * @param <T>: the class type of the object
     * @return the encrypted string
     */
    public <T> String encryptObjectToString(T object) {

        if(object == null)
            return null;

        try {
            return RsaAesGcmStandard.encryptTextUsingAES(stringifyObject(object), payloadEncKey);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new NrhException(ResponseEnum.ERROR, "Couldn't encrypt payload");
        }
    }



    /**
     * Decrypts a string and returns the object value
     * @param objectString: the string to decrypt
     * @param <T>: the class type of the target object
     * @return the target object
     */
    public <T> T decryptStringToObject(String objectString, Class<T> targetObjectClass) {

        try {
            return toObject(RsaAesGcmStandard.decryptTextUsingAES(objectString, payloadEncKey), targetObjectClass);
        } catch (Exception e) {
            log.info("Couldn't decrypt request payload");
            log.error(e.getMessage(), e);
            throw new NrhException(ResponseEnum.ERROR, "Error occurred while decrypting request payload");
        }
    }

    public String generateSHA512(String password) {//   password = unique ref+ amount + beneficiary account
        log.info(":::::::::::::this param request :::::::::::" + password);
        StringBuffer hexString = new StringBuffer();
        String hashkey = "(nmi#90$sSDF5cgy62372ll>";

        password = hashkey + password;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(password.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            hexString = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }

        return hexString.toString();
    }
}