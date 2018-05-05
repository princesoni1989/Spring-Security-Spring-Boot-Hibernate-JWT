package com.spring.boot_security_hibernate.co;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class LoginCo {

    @NotBlank(message = "Please provide username")
    private String username;

    @NotBlank(message = "Please provide password")
    private String password;


}
