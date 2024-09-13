package com.springtest.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiResponse<T> {
    // Getters and Setters
    private int code;        // 状态码
    private String message;   // 提示信息
    private T data;           // 泛型数据

    public ApiResponse() {
    }

    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 成功的静态方法
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(20000, "success", data);
    }

    // 失败的静态方法
    public static <T> ApiResponse<T> failure(String message) {
        return new ApiResponse<>(50000, message, null);
    }

}
