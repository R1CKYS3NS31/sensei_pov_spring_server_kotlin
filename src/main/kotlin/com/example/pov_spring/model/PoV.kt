package com.example.pov_spring.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.*

@Entity
@Table(schema = "pov_schema", name = "povs")
data class PoV(
    @Id
    val id: UUID? = UUID.randomUUID(),
    val title: String?,
    val points: String?,
    val author: String?,
    val attachment: String?,
    @UpdateTimestamp
    val updatedAt: Instant = Instant.now(),
    val createdAt: Instant = Instant.now()
)
