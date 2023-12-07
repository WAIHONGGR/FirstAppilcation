package com.transaction.product.process.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .requestMatchers(request -> "/api/merchant/**"
                        .equals(request.getServletPath())).hasRole("MERCHANT")
                .requestMatchers(request -> "/api/transaction/**"
                        .equals(request.getServletPath())).hasRole("TRANSACTION")
                .anyRequest().authenticated()
                .and()
                .csrf().disable(); // Disable CSRF for simplicity (enable it in production)

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails adminUser = User.builder()
                .username("transaction")
                .password(passwordEncoder().encode("password"))
                .roles("TRANSACTION")
                .build();

        UserDetails merchantUser = User.builder()
                .username("merchant")
                .password(passwordEncoder().encode("password"))
                .roles("MERCHANT")
                .build();

        return new InMemoryUserDetailsManager(adminUser, merchantUser);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
