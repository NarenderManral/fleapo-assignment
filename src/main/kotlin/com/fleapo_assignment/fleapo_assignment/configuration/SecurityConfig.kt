package com.fleapo_assignment.fleapo_assignment.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
open class SecurityConfig {

    @Bean
    open fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable() // Disable CSRF for simplicity, you might enable it depending on your use case
//            .authorizeHttpRequests { authz ->
//                authz.antMatchers("/api/signup", "/api/login").permitAll()
//                authz.anyRequest().authenticated()
//            }
        return http.build()
    }
}

