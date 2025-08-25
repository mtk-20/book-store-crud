package com.example.book_store_management.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class BasicSecurity {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(c -> c.disable())
                .authorizeHttpRequests(request -> (
                        request
                                .requestMatchers("/api/book/**", "/api/author/**").permitAll())
                        .requestMatchers("/api/create-book", "/api/delete/{id}", "/api/update/{id}",
                                "/api/create-author").hasRole("ADMIN").anyRequest().authenticated()
                )
                .httpBasic(h -> {
                })
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var admin = User.withUsername("john")
                .password("{noop}john")
                .roles("ADMIN")
                .build();

        var user1 = User.withUsername("annie")
                .password("{noop}annie")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user1);
    }
}
