package com.ecobank.srms.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ResponseDTO<T> {

    @JsonIgnore //INSTANCE VARIABLES access them all with get and set - lombok @Data annotation, @AllArgsConstructor takes all as constructor!
    private boolean isSuccessful;
    private String responseMessage;
    private String responseCode;
    private T data;

    @JsonIgnore
    private String token;
//tail -f /usr/app/logs/EcoOtpService.log
    public ResponseDTO(boolean isSuccessful, String message, ResponseCodes responseCodes) {
        this.responseMessage=message;
        this.isSuccessful=isSuccessful;
        this.responseCode=responseCodes.getCode();
    }
//overloaded ResponseDTO constructor
    public ResponseDTO(boolean isSuccessful, String message, ResponseCodes responseCodes, T payload){
        this.responseMessage=message;
        this.isSuccessful=isSuccessful;
        this.responseCode=responseCodes.getCode();
        this.data=payload;
    }
}
