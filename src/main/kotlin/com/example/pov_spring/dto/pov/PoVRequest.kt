package com.example.pov_spring.dto.pov

import com.example.pov_spring.model.PoV


data class PoVRequest(
    val title: String?,
    val points: String?,
    val author: String?,
    val attachment: String?,
)

fun PoVRequest.toModel(): PoV =
    PoV(
        title = title,
        points = points,
        author = author,
        attachment = attachment,
    )
