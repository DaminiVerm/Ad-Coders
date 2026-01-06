package com.divya.APIGateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(auth -> auth
                        .pathMatchers("/auth/**").permitAll()   // login service open
                        .pathMatchers("/student/**").hasRole("STUDENT")
                        .pathMatchers("/company/**").hasRole("COMPANY")
                        .pathMatchers("/placement/**").hasRole("ADMIN")
                        .anyExchange().authenticated()
                )
                .formLogin(Customizer.withDefaults())  // session based
                .logout(Customizer.withDefaults());

        return http.build();
    }
}
