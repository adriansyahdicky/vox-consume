package com.vox.voxconsumeapi.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @Autowired
    ObjectMapper objectMapper;

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<Object> UnAuthorizedException(UnAuthorizedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UnProcessableException.class)
    public ResponseEntity<Object> UnProcessableException(UnProcessableException ex) throws JsonProcessingException {
        Map<String, Object> map=objectMapper.readValue(ex.getMessage(), Map.class);
        return new ResponseEntity<>(map, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> NotFoundException(NotFoundException ex) throws JsonProcessingException {
        Map<String, Object> map=objectMapper.readValue(ex.getMessage(), Map.class);
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

}
