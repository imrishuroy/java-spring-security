package com.rishu.springsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(
                User.withDefaultPasswordEncoder()
                        .username("rishu")
                        .password("1234")
                       // .authorities("read")
                        .roles("USER")
                        .build());


        userDetailsManager.createUser(
                org.springframework.security.core.userdetails.User.withUsername("admin")
                        .password("12345")
                        //.authorities("read", "write")
                        .roles("ADMIN")
                        .build());

        return userDetailsManager;

    }
}
