package com.illarli.middleware.resolver;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class HandlerValidException extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        Map<String, List<String>> body = new HashMap<>();
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        body.put("errors", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, List<String>> body = new HashMap<>();
        List<String> errors = new LinkedList<>();
        String error = "";
        try {
            error = ex.getMessage().split("DTO")[1];
        } catch (NullPointerException ignored) {
        }
        if (error.isEmpty()) {
            error = ex.getMessage();
        } else {
            error = error.substring(2, error.length() - 3) + " bad deserialize";
        }
        errors.add(error);
        body.put("errors", errors);
        if (ex.getRootCause() instanceof InvalidFormatException) {
            try {
                System.out.println(ex.getHttpInputMessage().getBody());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}

