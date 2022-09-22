package com.ecobank.srms.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DisplayPictureResponse {
    private String message;
    private String picUrl;
    private String code;

}
