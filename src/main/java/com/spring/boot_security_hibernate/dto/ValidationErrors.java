package com.spring.boot_security_hibernate.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ValidationErrors {

    private List<FieldErrorDto> errors = new ArrayList<>();

    public void addError(FieldErrorDto fieldErrorDto) {
        errors.add(fieldErrorDto);
    }

}
