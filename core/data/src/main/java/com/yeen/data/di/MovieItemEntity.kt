package com.yeen.data.di

import java.io.Serializable

open class MovieItemEntity(
    open val genre: List<String> = listOf(),
    open val thumbUrl: String = "",
    open val title: String = "",
    open val rating: Float = 0F,
    open val year: Int? = 0
) : Serializable
