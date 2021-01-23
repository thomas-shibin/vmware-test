package com.test.core.empstore.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 * Created by shibthom on 7/11/2020.
 */
@Component
@ControllerAdvice
public class CustomReponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllExceptions(
            Exception ex, WebRequest wr
    ){
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), wr.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpHeaders headers, HttpStatus status,
                                                               WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeStoreCustomException.class)
    public ResponseEntity<ExceptionResponse> handleAllExceptions(
            EmployeeStoreCustomException ex, WebRequest wr
    ){
        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), wr.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
