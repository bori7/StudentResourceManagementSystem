package com.ecobank.srms.dto;

import com.ecobank.srms.utils.ResponseCodes;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseDTO {
    private String message;

    private Map<String, String> errorFields;
    private String responseCode;

    public ErrorResponseDTO(ResponseCodes responseCode, String errorMessage){
        this.responseCode = responseCode.getCode();
        this.message = errorMessage;
    }

    public ErrorResponseDTO(ResponseCodes responseCode, Map<String, String> errors, String errorMessage){
        this.responseCode = responseCode.getCode();
        this.errorFields = errors;
        this.message = errorMessage;
    }
}
