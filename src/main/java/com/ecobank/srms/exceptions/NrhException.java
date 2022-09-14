package com.ecobank.srms.exceptions;

import com.ecobank.srms.utils.ResponseEnum;
import com.ecobank.srms.utils.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;


/* @author sa_oladipo created on 3/24/22 */

@EqualsAndHashCode(callSuper = true)
@Data
public class NrhException extends RuntimeException{

    private ResponseEnum responseEnum;
    private String message;
    private String code;

    public NrhException(ResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.responseEnum = responseEnum;
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }
    
    public NrhException(ResponseEnum responseEnum, String message) {
        super((!StringUtils.isEmptyBlank(message)) ? message : responseEnum.getMessage());
        this.responseEnum = responseEnum;
        this.code = responseEnum.getCode();
        this.message = (!StringUtils.isEmptyBlank(message) ? message : responseEnum.getMessage());
    }
    
    public NrhException(String code, String message) {
        super(message);
        this.code = code;;
        this.message = message;
    }
}
