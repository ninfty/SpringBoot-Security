package com.sbsecurity.sb_security.config;

import com.sbsecurity.sb_security.domain.entity.User;
import com.sbsecurity.sb_security.domain.security.CustomAuthentication;
import com.sbsecurity.sb_security.domain.security.UserIdentification;
import com.sbsecurity.sb_security.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = (String) authentication.getCredentials();

        User user = userService.getUserWithPermissions(login);
        if(user != null){
            boolean passwordMatch = passwordEncoder.matches(password, user.getPassword());
            if(passwordMatch){
                UserIdentification userIdentification = new UserIdentification(
                        user.getId(),
                        user.getName(),
                        user.getLogin(),
                        user.getPermissions()
                );

                return new CustomAuthentication(userIdentification);
            }

        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
