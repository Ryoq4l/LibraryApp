package org.tisi.controller.advice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.tisi.exceptions.BusinessException;

@RestControllerAdvice
public class MyExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> businessException(BusinessException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST  )
                .body(new ErrorMessage(exception.getMessage()));
    }

}