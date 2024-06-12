package com.yeen.data.repository

import com.yeen.data.di.MovieDetailEntity
import com.yeen.data.entity.CategoryEntity
import com.yeen.data.entity.EntityWrapper
import com.yeen.data.mapper.CategoryMapper
import com.yeen.data.mapper.FeedConstants
import com.yeen.data.order.IStorage
import com.yeen.data.order.SortOrder
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieNetworkAPi : IMovieAppNetworkApi,
    private val storage: IStorage,
    private val categoryMapper: CategoryMapper
): IMovieDataSource{

    override suspend fun getCategories(sortOrder: SortOrder?): EntityWrapper<List<CategoryEntity>> {
        return categoryMapper.mapFromResult(
            result = movieNetworkAPi.getMovies(),
            extra = sortOrder
        )
    }

    override suspend fun getMovieDetail(movieName: String): MovieDetailEntity {
        return storage
            .get<List<MovieDetailEntity>>(FeedConstants.MOVIE_LIST_KEY)
            ?.single { it.title == movieName }
            ?: MovieDetailEntity()
    }
}