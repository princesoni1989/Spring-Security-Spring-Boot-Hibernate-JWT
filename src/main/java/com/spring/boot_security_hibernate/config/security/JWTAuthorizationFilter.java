package com.spring.boot_security_hibernate.config.security;

import com.spring.boot_security_hibernate.dao.repository.AuthenticationTokenRepository;
import com.spring.boot_security_hibernate.entity.AuthenticationToken;
import com.spring.boot_security_hibernate.entity.User;
import io.jsonwebtoken.Jwts;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{
    private AuthenticationTokenRepository authenticationTokenRepository;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, AuthenticationTokenRepository authenticationTokenRepository){
        super(authenticationManager);
        this.authenticationTokenRepository = authenticationTokenRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(SecurityConstants.HEADER_STRING);
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(token));
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if (token != null) {
            String username = null;
            try {
                username = Jwts.parser()
                        .setSigningKey(SecurityConstants.SECRET)
                        .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                        .getBody()
                        .getSubject();
            } catch (Exception e) {
                throw new AccessDeniedException(SecurityConstants.TOKEN_PREFIX + " is not valid");
            }
            if (username != null) {
                AuthenticationToken authenticationToken = authenticationTokenRepository.findAuthenticationTokenByToken(token);
                User user = authenticationToken.getUser();
                return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
            }
        }
        return null;
    }
}
