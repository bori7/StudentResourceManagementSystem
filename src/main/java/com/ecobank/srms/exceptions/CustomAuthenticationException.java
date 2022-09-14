package com.ecobank.srms.exceptions;

public class CustomAuthenticationException extends  RuntimeException{

    public CustomAuthenticationException(String message){
        super(message);
    }
}
