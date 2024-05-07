package com.yeen.data.request

import kotlinx.serialization.Serializable


@Serializable
data class MovieRequest(
    var sort_by : String,
    var language : String,
    var page : Int
)

