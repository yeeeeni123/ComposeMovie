package com.yeen.data.response

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class MovieAreaModel (
    val selectedArea: MovieArea,
    val visibleAreas: List<MovieArea>
) {
    data class MovieArea (val key: Int, val name: String, var clickable: Boolean)
}


data class MovieAreaSubModel (
    val selectedAreaSub: MovieAreaSub,
    val visibleAreaSub: List<MovieAreaSub>
) {
    data class MovieAreaSub(
        val area: String,
        var isSelected: Boolean
    )
}

