package com.example.pov_spring.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity.authorizeHttpRequests {
            it.requestMatchers("/**","/api/povs/**").permitAll()
//            it.anyRequest().permitAll() // ricky has bugs: unable to post
//            it.requestMatchers("/api/auth/**").permitAll().anyRequest().authenticated()
        }
//            .httpBasic(withDefaults()).formLogin(withDefaults()).logout {
//            it.permitAll()
//        }

        return httpSecurity.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        return InMemoryUserDetailsManager(
//            User.withUsername("user").password("pass").passwordEncoder {
//                BCryptPasswordEncoder().encode(it)
//            }.build() // ricky has bugs: no passwordEncoder
            User.withDefaultPasswordEncoder().username("user").password("pass").build()
        )
    }

//    @Bean
//    fun passwordEncoder(): PasswordEncoder {
//        return BCryptPasswordEncoder()
//    }
}