package com.ecobank.srms.exceptions;

import com.ecobank.srms.utils.ResponseCodes;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

    @Data
    @Accessors(chain = true)
    public class GenericException extends RuntimeException {

        private HttpStatus httpStatus;
        private ResponseCodes code;
        private String message;

        public GenericException(ResponseCodes code, String message, HttpStatus httpStatus){
            super(message);
            this.code = code;
            this.message = message;
            this.httpStatus = httpStatus;
        }
    }

