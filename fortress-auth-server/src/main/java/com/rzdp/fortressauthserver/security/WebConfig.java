package com.rzdp.fortressauthserver.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class WebConfig extends WebSecurityConfigurerAdapter {

    private static final String USER_NAME = "john.carnell";
    private static final String USER_PASSWORD = "password1";
    private static final String ADMIN_NAME = "willliam.woodward";
    private static final String ADMIN_PASSWORD = "password2";
    private static final String ROLE_USER = "USER";
    private static final String ROLE_ADMIN = "ADMIN";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(USER_NAME)
                .password(USER_PASSWORD)
                .roles(ROLE_USER).and()
                .withUser(ADMIN_NAME)
                .password(ADMIN_PASSWORD)
                .roles(ROLE_USER, ROLE_ADMIN);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }
}
