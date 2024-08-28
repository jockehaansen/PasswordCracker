package com.example.javasecpasswordcracker.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

            http
                    .authorizeHttpRequests(authorize -> authorize
                            .requestMatchers("/css/**", "/js/**", "/login").permitAll()
                            .anyRequest().authenticated()
                    )
                    .oauth2Login(oauth2 -> oauth2
                            .loginPage("/login")
                            .defaultSuccessUrl("/", true)
                            .failureUrl("/login?error=true")
                    )
                    .formLogin(form -> form
                            .loginPage("/login")
                            .defaultSuccessUrl("/", true)
                            .failureUrl("/login?error=true")
                    )
                    .logout(logout -> logout
                            .permitAll()
                            .logoutUrl("/perform_logout")
                            .deleteCookies("JSESSIONID")
                            .invalidateHttpSession(true)
                            .clearAuthentication(true)
                            .logoutSuccessUrl("/logout-success") // Redirect after logout
                    );

            return http.build();
        }
    }

