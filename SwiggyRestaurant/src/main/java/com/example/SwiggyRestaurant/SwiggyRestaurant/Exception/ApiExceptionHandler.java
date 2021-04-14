package com.example.SwiggyRestaurant.SwiggyRestaurant.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException exp){

        ApiException apiException = new ApiException(
        		exp.getMessage(),
        		exp,
        		HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }

}