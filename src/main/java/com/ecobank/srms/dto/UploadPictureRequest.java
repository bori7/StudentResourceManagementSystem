package com.ecobank.srms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UploadPictureRequest {
    private String jambNo;
    private String picture;
}
