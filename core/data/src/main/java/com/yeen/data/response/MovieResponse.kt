package com.yeen.data.response

import kotlinx.serialization.Serializable


@Serializable
data class MovieResponse(
    var page : Int,
    var results : List<MovieItem>
)

