package com.ecobank.srms.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BioMedDataResponse {
    private String message;
    private String code;
    private String url;
    private String jambNo;
}
