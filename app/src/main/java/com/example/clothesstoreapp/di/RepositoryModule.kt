package com.example.clothesstoreapp.di


import com.example.clothesstoreapp.datasource.localdatasource.AppDatabase
import com.example.clothesstoreapp.datasource.network.api.ApiService
import com.example.clothesstoreapp.datasource.network.utils.ResponseConverter
import com.example.clothesstoreapp.datasource.repository.Repository
import com.example.clothesstoreapp.datasource.repositoryimpl.RepositoryImpl
import com.example.clothesstoreapp.datasource.utils.BasketMapper
import com.example.clothesstoreapp.datasource.utils.WishlistMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dependency injection class that provides instances related to repositories
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideResponseConverter(): ResponseConverter {
        return ResponseConverter()
    }

    @Provides
    @Singleton
    fun provideWishListMapper() = WishlistMapper()

    @Provides
    @Singleton
    fun provideBasketMapper() = BasketMapper()


    @Provides
    @Singleton
    fun provideRepository(
        apiService: ApiService,
        responseConverter: ResponseConverter,
        appDatabase: AppDatabase,
        wishlistMapper: WishlistMapper,
        basketMapper: BasketMapper
    ): Repository {
        return RepositoryImpl(apiService, responseConverter, appDatabase, wishlistMapper, basketMapper)
    }
}