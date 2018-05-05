package com.spring.boot_security_hibernate.bootstrap;

import com.spring.boot_security_hibernate.dao.repository.UserRepository;
import com.spring.boot_security_hibernate.entity.Role;
import com.spring.boot_security_hibernate.entity.User;
import com.spring.boot_security_hibernate.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class bootstrap implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("********************** Server Started *****************");
        //Write bootstrap login here, like creating admin user
        //        String password = bCryptPasswordEncoder.encode("password");
        //        Set<Role> roleSet = new HashSet<>();
        //        Role role = new Role(UserRole.ADMIN);
        //        User user = new User("prince@gmail.com", "prince.soni", password, "prince", "soni", "", true, true, true, true, true, roleSet);
        //        role.setUser(user);
        //        roleSet.add(role);
        //        userRepository.save(user);

    }
}
