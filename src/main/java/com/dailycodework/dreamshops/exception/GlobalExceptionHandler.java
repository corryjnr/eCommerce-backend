package com.dailycodework.dreamshops.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handAccessDeniedException(AccessDeniedException exception){
        String message = "You do not have permission to perform this action";
        return new ResponseEntity<>(message, FORBIDDEN);
    }
}
