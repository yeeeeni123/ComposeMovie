package com.yeen.domain.usecase

import com.yeen.data.entity.CategoryEntity
import com.yeen.data.entity.EntityWrapper
import com.yeen.data.order.SortOrder
import com.yeen.data.repository.IMovieDataSource
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(
    private val dataSource: IMovieDataSource
): IGetCategoryUseCase {
    override suspend fun invoke(sortOrder: SortOrder?): EntityWrapper<List<CategoryEntity>> {
        return dataSource.getCategories(sortOrder)
    }
}