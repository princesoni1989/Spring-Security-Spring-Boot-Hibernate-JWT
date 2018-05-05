package com.spring.boot_security_hibernate.entity;

import com.spring.boot_security_hibernate.enums.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToOne
    private User user;

    public Role(UserRole role) {
        this.role = role;
    }

}
