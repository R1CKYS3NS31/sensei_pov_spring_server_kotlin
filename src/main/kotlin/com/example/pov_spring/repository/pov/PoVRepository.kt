package com.example.pov_spring.repository.pov

import com.example.pov_spring.model.PoV
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

interface PoVRepository : CoroutineCrudRepository<PoV, UUID> {
    suspend fun findPoVByTitle(title: String): Flow<PoV>
}