package com.spring.boot_security_hibernate.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldErrorDto {

    private String message;
    private String field;

    public FieldErrorDto(String message, String field) {
        this.message = message;
        this.field = field;
    }

    public FieldErrorDto() {
    }

}
