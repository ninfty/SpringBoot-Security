package com.sbsecurity.sb_security.config;

import com.sbsecurity.sb_security.domain.security.CustomAuthentication;
import com.sbsecurity.sb_security.domain.security.UserIdentification;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MasterAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var user = authentication.getName();
        var pass = (String) authentication.getCredentials();

        String userMaster = "master";
        String passMaster = "@321";

        if (userMaster.equals(user) && passMaster.equals(pass)) {
            UserIdentification userIdentification = new UserIdentification(
                    "Master",
                    "Master",
                    userMaster,
                    List.of("ADMIN"));

            return new CustomAuthentication(userIdentification);
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
