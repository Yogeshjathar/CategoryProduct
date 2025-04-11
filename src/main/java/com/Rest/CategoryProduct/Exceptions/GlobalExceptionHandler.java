package com.Rest.CategoryProduct.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundExceptions.class)
    public ResponseEntity<String> resourceNotFoundHandler(ResourceNotFoundExceptions ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

   @ExceptionHandler(Exception.class)
    public ResponseEntity<String> generalExceptionHandler(Exception ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
   }

   @ExceptionHandler(DataAlreadyExistException.class)
    public ResponseEntity<String> DataAlreadyExistExceptionHandler(DataAlreadyExistException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
   }
}
