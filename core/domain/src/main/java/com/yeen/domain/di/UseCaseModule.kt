package com.yeen.domain.di

import com.yeen.domain.usecase.GetCategoryUseCase
import com.yeen.domain.usecase.IGetCategoryUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {


    @Singleton
    @Binds
    abstract fun bindGetMovieListUseCase(getMovieListUseCase: GetCategoryUseCase) : IGetCategoryUseCase

}