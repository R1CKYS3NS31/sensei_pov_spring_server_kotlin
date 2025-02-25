package com.example.pov_spring.config.security

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer


class SecurityWebApplicationInitializer : AbstractSecurityWebApplicationInitializer() {
    override fun enableHttpSessionEventPublisher(): Boolean {
        return true
    }
}