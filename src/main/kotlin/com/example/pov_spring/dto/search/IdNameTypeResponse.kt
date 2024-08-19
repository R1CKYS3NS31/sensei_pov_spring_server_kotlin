package com.example.pov_spring.dto.search

import com.example.pov_spring.model.PoV
import java.util.*

data class IdNameTypeResponse(
    val id: UUID, val name: String?, val type: ResultType
)

enum class ResultType {
    USER, POV
}

fun PoV.toIdNameTypeResponse(): IdNameTypeResponse = IdNameTypeResponse(
    id = this.id!!, name = title, type = ResultType.POV
)