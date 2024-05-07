package com.yeen.data.response

import kotlinx.serialization.Serializable

@Serializable
data class MovieItem(
    val vote_count: Int,
    val vote_average: Float,
    val title: String,
    val release_date: String,
    val poster_path: String,
    val overview: String?
)
