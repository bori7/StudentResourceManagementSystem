package com.ecobank.srms.exceptions;

import com.ecobank.srms.dto.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ecobank.srms.utils.ResponseCodes.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * Handles method validation exceptions
     * @param ex: the exception
     * @return the response entity
     */

    /**
     * Handles constraint validation exceptions
     * @param ex: the exception
     * @return the response entity
     */
    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<ErrorResponseDTO> handleConstraintValidations(ConstraintViolationException ex){
        String errors = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(" | "));
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(BAD_DATA, errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handle(Exception ex, HttpServletRequest request, HttpServletResponse response) {

        log.error("Exception occurred while processing the request: {} because: {}",request.getRequestURI(),ex.getMessage(),ex);
        return ResponseEntity.internalServerError().body((new ErrorResponseDTO(PROCESS_ERROR,"An unknown exception just occurred. Please try again or contact administrator")));
    }
    @ExceptionHandler(CustomAuthenticationException.class)
    public ResponseEntity<ErrorResponseDTO>
    handleAuthenticationException(CustomAuthenticationException ex){
        log.error("Exception occurred while trying to authenticate user because: {}",ex.getMessage());
        return new ResponseEntity<>(new ErrorResponseDTO(VALIDATION_ERROR,ex.getMessage()),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationException(HttpServletRequest request, ValidationException ex){
        log.error("validation exception occurred while trying to process request: {} because: {}",request.getRequestURI(),ex.getMessage());
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(VALIDATION_ERROR,ex.getMessage()));
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(HttpServletRequest request,
                                                                   GenericException ex){
        log.error("generic exception occurred while trying to process request: {} because: {}",request.getRequestURI(),ex.getMessage());
        return ResponseEntity.status((ex.getHttpStatus() == null) ?
                HttpStatus.INTERNAL_SERVER_ERROR : ex.getHttpStatus()).body(new ErrorResponseDTO(ex.getCode(),
                (ex.getMessage() == null) ? ex.getCode().getMessage() : ex.getMessage()));
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status( HttpStatus.BAD_REQUEST )
                .body(new ErrorResponseDTO(VALIDATION_ERROR, errors, "Invalid Inputs"));
    }

}
