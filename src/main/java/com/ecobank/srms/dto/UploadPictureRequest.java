package com.ecobank.srms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UploadPictureRequest {
    @NotNull(message = "JambNo cannot be null")
    @Size(min = 4,message = "jambNo must be more than 4")
    private String jambNo;

    @NotNull(message = "Picture cannot be null")
    private String picture;
}
