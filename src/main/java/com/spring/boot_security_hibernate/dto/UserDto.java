package com.spring.boot_security_hibernate.dto;

import com.spring.boot_security_hibernate.entity.User;
import com.spring.boot_security_hibernate.enums.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDto {

    private String email;

    private String username;

    private String firstName;

    private String lastName;

    private Boolean active;

    private Set<UserRole> roles;


    public UserDto getInstance(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setActive(user.getActive());
        Set<UserRole> userRoles = new HashSet<>();
        user.getRoles().forEach(role -> userRoles.add(role.getRole()));
        userDto.setRoles(userRoles);
        return userDto;
    }

    public List<UserDto> getInstance(Iterable<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(getInstance(user));
        }
        return userDtos;
    }

}
