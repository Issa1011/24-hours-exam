package org.example.exam24hours.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        return http

                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/api/sensor-data")
                        .permitAll()

                        .requestMatchers(
                                "/api/alerts/active",
                                "/api/alerts/*/reports"
                        )
                        .hasAnyRole("USER", "ADMIN")

                        .anyRequest()
                        .hasRole("ADMIN")
                )

                .httpBasic(Customizer.withDefaults())

                .build();
    }


    @Bean
    public InMemoryUserDetailsManager users() {

        UserDetails admin = User
                .withDefaultPasswordEncoder()
                .username("admin")
                .password("123")
                .roles("ADMIN")
                .build();


        UserDetails user = User
                .withDefaultPasswordEncoder()
                .username("user")
                .password("123")
                .roles("USER")
                .build();


        return new InMemoryUserDetailsManager(admin, user);
    }
}