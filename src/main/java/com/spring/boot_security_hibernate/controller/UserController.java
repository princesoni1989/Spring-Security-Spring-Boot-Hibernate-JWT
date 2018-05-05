package com.spring.boot_security_hibernate.controller;

import com.spring.boot_security_hibernate.co.UserCo;
import com.spring.boot_security_hibernate.dto.FieldErrorDto;
import com.spring.boot_security_hibernate.dto.ResponseHandler;
import com.spring.boot_security_hibernate.dto.UserDto;
import com.spring.boot_security_hibernate.dto.ValidationErrors;
import com.spring.boot_security_hibernate.services.UserService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CommonsLog
@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    //Create user
    @PostMapping("/signup")
    public ResponseHandler<UserDto> signup(@Valid @RequestBody UserCo userCo) {
        log.info("******SignUp *****");
        UserDto userDto = userService.saveUser(userCo);
        return new ResponseHandler<UserDto>(200, userDto);
    }

    //Get logged in user
    @GetMapping("/loggedInUser")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseHandler<UserDto> getLoggedInUser() {
        log.info("******loggedInUser *****");
        return new ResponseHandler<UserDto>(200, userService.getLoggedinUser());
    }

    //Get User by id
    @GetMapping("/user/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseHandler<UserDto> getUserById(@PathVariable int id) {
        log.info("******getUserById *****");
        return new ResponseHandler<UserDto>(200, userService.getUserById(id));
    }

    //Get list of user
    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseHandler<List<UserDto>> getUsers(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer per_page) {
        log.info("******getUsers *****");
        return new ResponseHandler<List<UserDto>>(200, userService.getUsers(page, per_page));
    }

    //Update user
    @PutMapping("/user/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseHandler<UserDto> updateUser(@PathVariable int id, @Valid @RequestBody UserCo userCo) {
        log.info("******updateUser *****");
        UserDto userDto = userService.updateUser(id, userCo);
        return new ResponseHandler<UserDto>(200, userDto);
    }

    //delete user
    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseHandler<UserDto> deleteUserById(@PathVariable int id) {
        log.info("******deleteUserById *****");
        userService.deleteUserById(id);
        return new ResponseHandler<UserDto>(200, "User Deleted SuccessFully");
    }

}
