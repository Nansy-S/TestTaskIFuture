package com.prokopovich.balanceapi.webapp.controller;

import com.prokopovich.balanceapi.domain.exception.BankAccountServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(BankAccountServiceException.class)
    public ResponseEntity<String> handleApplicationServiceException(BankAccountServiceException e) {

        log.error(e.getMessage(), e.getCause());
        var jsonResponse = (e.getMessage());

        return new ResponseEntity<>(jsonResponse, HttpStatus.valueOf(e.getStatusCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        log.error(e.getMessage(), e.getCause());
        var jsonResponse = (e.getMessage());

        return new ResponseEntity<>(jsonResponse, HttpStatus.BAD_REQUEST);
    }
}
