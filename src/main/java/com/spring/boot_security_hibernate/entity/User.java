package com.spring.boot_security_hibernate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "User name is required")
    @Column(unique = true)
    private String username;

    private String firstName;

    private String lastName;

    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Role> roles;

    private Boolean active = false;

    private boolean accountNotExpired = true;

    private boolean accountNotLocked = true;

    private boolean credentialNotExpired = true;

    private boolean isEnabled = true;

    @PrePersist
    public void encryptPassword(){
        this.password = new BCryptPasswordEncoder().encode(this.password);
    }

    public User(String email, String username, String password, String firstName, String lastName, String photo, Boolean active, boolean accountNotExpired, boolean accountNotLocked, boolean credentialNotExpired, boolean isEnabled, Set<Role> roles) {
        super();
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.accountNotExpired = accountNotExpired;
        this.accountNotLocked = accountNotLocked;
        this.credentialNotExpired = credentialNotExpired;
        this.isEnabled = isEnabled;
        this.roles = roles;
    }


    public User(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.active = user.getActive();
        this.roles = user.getRoles();
        this.id = user.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole())).
                collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNotExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNotLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialNotExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

}
