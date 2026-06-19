package org.example.exam24hours.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                                .requestMatchers(HttpMethod.POST, "/api/sensor-data").permitAll()

                                .requestMatchers(HttpMethod.GET, "/api/alerts/active").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/alerts/*/reports").hasAnyRole("USER", "ADMIN")


                                .requestMatchers(HttpMethod.GET, "/api/sensor-data/readings").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/api/alerts/*/status").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/alerts/*/reports").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/alerts/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/alerts").hasRole("ADMIN")

                                .anyRequest().authenticated()
                        )
                        .formLogin(Customizer.withDefaults())
                        .logout(logout -> logout.permitAll())
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
