package com.example.pov_spring.controller.pov

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/povs")
class PoVController {
    @GetMapping
    suspend fun getPoVs() =
        "povs implementation..."

}

