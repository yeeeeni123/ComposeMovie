package com.yeen.domain.usecase

import com.yeen.data.entity.CategoryEntity
import com.yeen.data.entity.EntityWrapper
import com.yeen.data.order.SortOrder

interface IGetCategoryUseCase {
    suspend operator fun invoke(sortOrder: SortOrder? = null): EntityWrapper<List<CategoryEntity>>
}