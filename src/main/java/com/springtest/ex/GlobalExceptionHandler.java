package com.springtest.ex;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class) // 捕获 RuntimeException
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 返回400状态码
    public ErrorResponse handleRuntimeException(RuntimeException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage());
    }

    // 定义错误响应类
    @Data
    public static class ErrorResponse {
        private int code; // HTTP状态码
        private String status; // 状态信息
        private String message; // 错误信息

        public ErrorResponse(int code, String status, String message) {
            this.code = code;
            this.status = status;
            this.message = message;
        }


    }
}
