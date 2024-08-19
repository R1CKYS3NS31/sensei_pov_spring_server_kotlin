package com.example.pov_spring.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IndexController {
    @GetMapping("/")
    suspend fun index() = "Welcome @ PoV"
}