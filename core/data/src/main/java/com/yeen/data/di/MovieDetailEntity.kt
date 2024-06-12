package com.yeen.data.di

data class MovieDetailEntity(
    val actors: List<String> = listOf(),
    val desc: String = "",
    val directors: List<String> = listOf(),
    val imageUrl: String = "",
    val imdbPath: String = "",
    override val genre: List<String> = listOf(),
    override val thumbUrl: String = "",
    override val title: String = "",
    override val rating: Float = 0F,
    override val year: Int? = 0
) : MovieItemEntity()
