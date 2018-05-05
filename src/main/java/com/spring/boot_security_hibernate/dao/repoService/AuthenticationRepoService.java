package com.spring.boot_security_hibernate.dao.repoService;

import com.spring.boot_security_hibernate.dao.repository.AuthenticationTokenRepository;
import com.spring.boot_security_hibernate.entity.AuthenticationToken;
import com.spring.boot_security_hibernate.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationRepoService {

    @Autowired
    private AuthenticationTokenRepository authenticationTokenRepository;

    public void saveToken(User user, String token) {
        AuthenticationToken authenticationToken = new AuthenticationToken(token, user);
        authenticationTokenRepository.save(authenticationToken);
    }

    public void deleteToken(String token) {
        AuthenticationToken authenticationToken = authenticationTokenRepository.findAuthenticationTokenByToken(token);
        authenticationTokenRepository.delete(authenticationToken);
    }
}
