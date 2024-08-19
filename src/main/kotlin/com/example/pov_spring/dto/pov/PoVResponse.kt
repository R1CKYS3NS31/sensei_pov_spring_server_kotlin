package com.example.pov_spring.dto.pov

import com.example.pov_spring.model.PoV
import java.time.Instant
import java.util.*


data class PoVResponse(
    val id: UUID?,
    val title: String?,
    val points: String?,
    val author: String?,
    val attachment: String?,
    val updatedAt: Instant,
    val createdAt: Instant
)

fun PoV.toResponse(): PoVResponse =
    PoVResponse(
        id = id,
        title = title,
        points = points,
        author = author,
        attachment = attachment,
        updatedAt = updatedAt,
        createdAt = createdAt
    )
