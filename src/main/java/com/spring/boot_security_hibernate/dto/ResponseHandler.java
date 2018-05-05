package com.spring.boot_security_hibernate.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseHandler<T> {

    private int status;
    private String message;
    private T data;

    public ResponseHandler(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseHandler(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseHandler(int status, T data) {
        this.status = status;
        this.data = data;
    }
}
