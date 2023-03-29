package com.enigma.ecommerce.controller;

import com.enigma.ecommerce.Exception.DuplicateException;
import com.enigma.ecommerce.Exception.NotFoundException;
import com.enigma.ecommerce.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleAllException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("500", e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("404", e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleDuplicateException(DuplicateException e){
        return ResponseEntity.status(HttpStatus.IM_USED)
                .body(new ErrorResponse("409", e.getMessage()));
    }
}
