package com.example.pov_spring.controller.search

import com.example.pov_spring.dto.search.IdNameTypeResponse
import com.example.pov_spring.dto.search.toIdNameTypeResponse
import com.example.pov_spring.model.PoV
import com.example.pov_spring.service.PoVService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/search")
class SearchController(
    private val poVService: PoVService
) {
    @GetMapping
    suspend fun searchByName(
        @RequestParam("query") query: String
    ): Flow<IdNameTypeResponse> =  merge(
        poVService.findPovByTitleLike(query).map(PoV::toIdNameTypeResponse), // merge other searched params
    )
}