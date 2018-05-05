package com.spring.boot_security_hibernate.exceptions;


import com.spring.boot_security_hibernate.dto.FieldErrorDto;
import com.spring.boot_security_hibernate.dto.ResponseHandler;
import com.spring.boot_security_hibernate.dto.ValidationErrors;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
@CommonsLog
public class GlobalException {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseHandler<ValidationErrors> handleException(MethodArgumentNotValidException exception) {
        ValidationErrors validationErrors = new ValidationErrors();

        exception.getBindingResult().getFieldErrors().stream()
                .forEach(error -> validationErrors.addError(new FieldErrorDto(error.getDefaultMessage(), error.getField())));
        System.out.println(validationErrors);
        return new ResponseHandler<ValidationErrors>(400, "Error", validationErrors);
    }
}
