package com.yeen.data.repository

import com.yeen.data.di.MovieDetailEntity
import com.yeen.data.entity.CategoryEntity
import com.yeen.data.entity.EntityWrapper
import com.yeen.data.order.SortOrder
import javax.inject.Inject

interface IMovieDataSource {
    suspend fun getCategories(sortOrder: SortOrder?= null) : EntityWrapper<List<CategoryEntity>>
    suspend fun getMovieDetail(movieName: String): MovieDetailEntity
}