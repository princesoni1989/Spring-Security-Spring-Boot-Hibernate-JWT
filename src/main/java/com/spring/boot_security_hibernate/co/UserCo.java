package com.spring.boot_security_hibernate.co;

import com.spring.boot_security_hibernate.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserCo {

    @NotBlank(message = "Please enter user name")
    private String username;

    @NotBlank(message = "Please enter password")
    private String password;

    @NotBlank(message = "Please enter email, Ir is required field")
    private String email;

    @NotBlank(message = "Please enter first name")
    private String firstName;

    @NotBlank(message = "Please enter last name")
    private String lastName;

    private UserRole role;

    private Boolean active = true;


}
