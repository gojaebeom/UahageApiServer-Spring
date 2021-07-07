package kr.co.hohocompany.uahage.controller;


import kr.co.hohocompany.uahage.dto.ResponseBodyForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.BindException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity exceptionHandler(Exception e) {
        ResponseBodyForm responseData = ResponseBodyForm.builder()
                .message(e.getMessage())
                .data(false)
                .build();
        return ResponseEntity.internalServerError().body(responseData);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity bindExceptionHandler(BindException e) {
        ResponseBodyForm responseData = ResponseBodyForm.builder()
                .message(e.getMessage())
                .data(false)
                .build();
        return ResponseEntity.badRequest().body(responseData);
    }

    @ExceptionHandler(NoSuchFieldException.class)
    public ResponseEntity noSuchFieldExceptionHandler(NoSuchFieldException e) {
        ResponseBodyForm responseData = ResponseBodyForm.builder()
                .message(e.getMessage())
                .data(false)
                .build();
        return ResponseEntity.badRequest().body(responseData);
    }


}
