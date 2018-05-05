package com.spring.boot_security_hibernate.entity;

import com.spring.boot_security_hibernate.config.security.SecurityConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String token;

    private Long expirationTime;

    @ManyToOne
    private User user;

    public AuthenticationToken(String token, User user) {
        this.token = token;
        this.expirationTime = SecurityConstants.EXPIRATION_TIME;
        this.user = user;
    }

}
