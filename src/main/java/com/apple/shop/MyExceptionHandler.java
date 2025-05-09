package com.apple.shop;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

//@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handler(){
        return ResponseEntity.status(400).body("문자씀");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handler2(){
        return ResponseEntity.status(400).body("틀림");
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException() {
        // 아이디가 존재하지 않는 경우 처리
        return ResponseEntity.status(400).body("아이디 존재하지 않음");
    }
}
