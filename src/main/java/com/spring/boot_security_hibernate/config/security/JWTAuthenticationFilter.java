package com.spring.boot_security_hibernate.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot_security_hibernate.co.LoginCo;
import com.spring.boot_security_hibernate.dao.repoService.AuthenticationRepoService;
import com.spring.boot_security_hibernate.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private AuthenticationManager authenticationManager;
    private AuthenticationRepoService authenticationRepoService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationRepoService authenticationRepoService) {
        this.authenticationManager = authenticationManager;
        this.authenticationRepoService = authenticationRepoService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginCo loginCo = null;
        Authentication authentication = null;
        try {
            loginCo = new ObjectMapper().readValue(request.getInputStream(), LoginCo.class);
            System.out.println(loginCo);
            authentication = new UsernamePasswordAuthenticationToken(loginCo.getUsername(), loginCo.getPassword());
            return authenticationManager.authenticate(authentication);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        String token  = generateToken(user);
        authenticationRepoService.saveToken(user, token);
        response.addHeader(SecurityConstants.HEADER_STRING, token);
    }

    private String generateToken(User user) {
        return Jwts.builder().setSubject(user.getUsername()).setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET).compact();
    }
}

