package com.sportshoes.ecom.exceptions;

import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandlers extends RuntimeException {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public  ResponseEntity<String> JsonParseException(HttpMessageNotReadableException e) {
        System.out.println("caught this exception");
        log.info(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Invalid JSON Object Provided");
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityException(DataIntegrityViolationException e) {
        e.getMostSpecificCause();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMostSpecificCause().getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errorMsg = new HashMap<>();
        e.getBindingResult().getFieldErrors().stream().forEach(a->errorMsg.put(a.getField(), a.getDefaultMessage()));
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMsg.toString());
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleValidationException(ProductNotFoundException e) {
        System.out.println("catcing JST Exception");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(io.jsonwebtoken.MalformedJwtException.class)
    public ResponseEntity<String> handleJWTException(MalformedJwtException e) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("invalid JWt token");
    }
}
