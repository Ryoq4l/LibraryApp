package org.tisi.controller.advice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.tisi.exceptions.BusinessException;
import org.tisi.exceptions.GenericException;
import org.tisi.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class MyExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> businessException(BusinessException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST  )
                .body(new ErrorMessage(exception.getMessage()));
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(GenericException.class)
    public ResponseEntity<Object> GenericException(GenericException exception) {
        System.err.println("Generic Error:" + exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR  )
                .body(new ErrorMessage("Unexpected error"));
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> ResourceNotFoundException(ResourceNotFoundException exception) {
        System.err.println("Resource not found error:" + exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(exception.getMessage()));
    }


}