package com.uahage.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.BindException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity IllegalArgumentExceptionHandler(IllegalArgumentException e) {
        System.out.println("IllegalArgumentException: 에러 발생");
        System.out.println(e);
        Map<String, Object> response = new HashMap<>();
        String message = e.getMessage() != null ? e.getMessage() : "잘못된 요청입니다.";
        response.put("message", message);
        response.put("statusCode", 400);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity exceptionHandler(Exception e) {
        System.out.println("Exception: 에러 발생");
        System.out.println(e);
        Map<String, Object> response = new HashMap<>();
        String message = e.getMessage() != null ? e.getMessage() : "요청을 처리하지 못하였습니다.";
        response.put("message", message);
        response.put("statusCode", 500);
        return ResponseEntity.internalServerError().body(response);
    }
}
