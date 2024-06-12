package com.yeen.data.mapper

import com.yeen.data.di.MovieDetailEntity
import com.yeen.data.entity.CategoryEntity
import com.yeen.data.entity.EntityWrapper
import com.yeen.data.order.IStorage
import com.yeen.data.order.SortOrder
import com.yeen.data.response.MovieResponses
import javax.inject.Inject

class CategoryMapper @Inject constructor(
    private val storage: IStorage
) : BaseMapper<List<MovieResponses>, List<CategoryEntity>>() {
    override fun getSuccess(
        model: List<MovieResponses>?,
        extra: Any?
    ): EntityWrapper.Success<List<CategoryEntity>> {
        return model?.let {
            EntityWrapper.Success(
                entity = mutableListOf<MovieDetailEntity>()
                    .apply {
                        addAll(model.map { it.toMovieDetailEntity() })
                    }
                    .also {
                        storage.save(FeedConstants.MOVIE_LIST_KEY, it)
                    }
                    .map {
                        it.toMovieThumbnailEntity()
                    }
                    .toCategoryList(if (extra is SortOrder) extra else SortOrder.RATING)
            )
        } ?: EntityWrapper.Success(
            entity = listOf()
        )
    }

    override fun getFailure(error: Throwable): EntityWrapper.Fail<List<CategoryEntity>> {
        return EntityWrapper.Fail(error)
    }
}
