package com.example.ticketing

import com.yeen.data.di.MovieItemEntity

data class CategoryEntity(
    val id: Int,
    val genre: String,
    val movieEntities: List<MovieItemEntity>
)
