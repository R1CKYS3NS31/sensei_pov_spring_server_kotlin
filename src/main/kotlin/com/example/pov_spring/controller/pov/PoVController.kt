package com.example.pov_spring.controller.pov

import com.example.pov_spring.dto.pov.PoVRequest
import com.example.pov_spring.dto.pov.PoVResponse
import com.example.pov_spring.dto.pov.toModel
import com.example.pov_spring.dto.pov.toResponse
import com.example.pov_spring.model.PoV
import com.example.pov_spring.service.PoVService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
@RequestMapping("/api/povs")
class PoVController(
    private val poVService: PoVService, private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    @GetMapping("/test")
    suspend fun getPoVs() = "povs implementation..."


    @GetMapping("/")
    suspend fun retrievePoVS(
        @RequestParam("title", required = false) title: String?
    ): Flow<PoVResponse> = title?.let {
        poVService.findPovByTitleLike(title = it).map(PoV::toResponse)
    } ?: poVService.findPoVs().map(PoV::toResponse)

    @GetMapping("/{id}")
    suspend fun retrievePoV(@PathVariable id: UUID): PoVResponse =
        poVService.findPovById(id)?.toResponse()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "PoV not found")

    @PostMapping("/")
    suspend fun insertPoV(@RequestBody poVRequest: PoVRequest): PoVResponse =
        withContext(coroutineDispatcher) {
        poVService.savePoV(poV = poVRequest.toModel())?.toResponse().also {
            println(it)
        } ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)

    }

    @PutMapping("/")
    suspend fun updatePovById(
        @PathVariable id: UUID, @RequestBody poVRequest: PoVRequest
    ): PoVResponse =
        poVService.updatePovById(id, poV = poVRequest.toModel())?.toResponse()
            ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND, "PoV not found"
        )

    @DeleteMapping("/{id}")
    suspend fun deletePov(
        @PathVariable id: UUID
    ) {
        poVService.deletePovById(id)
    }
}

