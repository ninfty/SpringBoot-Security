package com.sbsecurity.sb_security.config;

import com.sbsecurity.sb_security.domain.security.CustomAuthentication;
import com.sbsecurity.sb_security.domain.security.UserIdentification;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class CustomFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String secretHeader = request.getHeader("x-secret");

        if (secretHeader != null) {
            if (secretHeader.equals("secret")) {
                var userIdentification = new UserIdentification(
                        "id-secret",
                        "Secret",
                        "x-secret",
                        List.of("USER")
                );

                Authentication authentication = new CustomAuthentication(userIdentification);

                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
