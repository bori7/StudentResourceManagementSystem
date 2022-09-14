package com.ecobank.srms.utils;



import com.ecobank.srms.encryption.EncryptDecryptUtil;
import com.ecobank.srms.exceptions.GenericException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;


/* @author sa_oladipo created on 1/24/22 */


@Slf4j
public class ResponseEntityUtils {


    /**
     * Returns successful response for controllers with method return holders
     *
     * @param payload: the payload
     * @param <T>:     the type
     * @return the response
     */
    public static <T> ResponseEntity<ResponseDTO<T>> getSuccessfulControllerResponse(T payload) {

        if (payload != null) {
            return ResponseEntity.ok().body(
                    new ResponseDTO<T>()
                            .setResponseCode(ResponseCodes.SUCCESS.getCode())
                            .setResponseMessage(ResponseCodes.SUCCESS.getMessage())
                            .setData(payload)
            );
        }
        return ResponseEntity.badRequest().body(
                new ResponseDTO<T>()
                        .setResponseCode(ResponseCodes.INVALID_CREDENTIAL.getCode())
                        .setResponseMessage(ResponseCodes.INVALID_CREDENTIAL.getMessage())
                        .setData(payload)
        );
    }


    /**
     * Returns successful response for controllers with general response holder
     *
     * @param payload: the general response payload
     * @param <T>:     the type
     * @return the response
     */
    public static <T> ResponseEntity<ResponseDTO<T>> getSuccessfulControllerGeneralResponse(ResponseDTO<T> payload) {

        return ResponseEntity.ok().body(payload);
    }


    /**
     * Returns successful encrypted response for controllers with method return holders
     *
     * @param payload: the payload
     * @return the response
     */
    public static ResponseEntity<String> getSuccessfulControllerResponseEncrypt(Object payload) {


        try {
            return ResponseEntity.ok().body(
                    EncryptDecryptUtil.encryptObjectToString(new ResponseDTO<>()
                            .setResponseCode(ResponseCodes.SUCCESS.getCode())
                            .setResponseMessage(ResponseCodes.SUCCESS.getMessage())
                            .setData(payload)
                    )
            );
        } catch (Exception e) {
            log.info("Couldn't encrypt response payload");
            log.error(e.getMessage(), e);
            throw new GenericException(ResponseCodes.PROCESS_ERROR, "Error occurred while encrypting payload", null);
        }
    }


    /**
     * Returns successful encrypted response for controllers with method return holders
     *
     * @param payload: the payload
     * @return the response
     */
    public static ResponseEntity<String> getSuccessfulControllerResponseEncrypt2(Object payload) {


        try {
            return ResponseEntity.ok().body(
                    EncryptDecryptUtil.encryptObjectToString(payload)
            );
        } catch (Exception e) {
            log.info("Couldn't encrypt response payload");
            log.error(e.getMessage(), e);
            throw new GenericException(ResponseCodes.PROCESS_ERROR, "Error occurred while encrypting payload", null);
        }
    }
}
