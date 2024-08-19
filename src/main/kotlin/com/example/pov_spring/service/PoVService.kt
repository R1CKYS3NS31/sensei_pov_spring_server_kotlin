package com.example.pov_spring.service

import com.example.pov_spring.model.PoV
import com.example.pov_spring.repository.pov.PoVRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.Instant
import java.util.*

@Service
class PoVService(private val poVRepository: PoVRepository) {
    suspend fun findPoVs(): Flow<PoV> = poVRepository.findAll()


    suspend fun findPovById(id: UUID): PoV? = poVRepository.findById(id)
        ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "PoV not found")

    suspend fun findPovByTitleLike(title: String): Flow<PoV> =
        poVRepository.findPoVByTitle(title = title)

    suspend fun savePoV(poV: PoV): PoV? =
        poV.title?.let {
            poVRepository.findPoVByTitle(it)
                .firstOrNull()
                ?.let { throw ResponseStatusException(HttpStatus.BAD_REQUEST, "PoV already exist") }
                ?: poVRepository.save(poV)
        } ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "PoV must have title")


    suspend fun updatePovById(id: UUID, poV: PoV): PoV? =
        poVRepository.findById(id)
            ?.let {
                poVRepository.save(
                    it.copy(
                        title = poV.title,
                        points = poV.points,
                        author = poV.author,
                        attachment = poV.attachment,
                        updatedAt = Instant.now()
                    )
                )
            }
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "PoV not found")


    suspend fun deletePovById(id: UUID) {
        poVRepository.findById(id)
            ?.let { poVRepository.deleteById(id) }
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "PoV not found")
    }
}