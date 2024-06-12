package com.yeen.domain.di

import com.yeen.data.helper.DataConverter
import com.yeen.data.helper.DataEncoding
import com.yeen.data.order.IStorage
import com.yeen.domain.pref.SharedPrefsStorageProvider
import com.yeen.domain.pref.StorageManager
import com.yeen.domain.pref.StorageProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Singleton
    @Provides
    fun bindOnDiscStorage(
        storage: StorageProvider,
        converter: DataConverter,
        encoding: DataEncoding
    ): IStorage = StorageManager(storage, converter, encoding)

    @Provides
    fun provideSharePrefStorageProvider(provider: SharedPrefsStorageProvider): StorageProvider = provider

}
