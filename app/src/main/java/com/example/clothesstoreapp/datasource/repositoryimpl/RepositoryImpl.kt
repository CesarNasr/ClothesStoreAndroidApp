package com.example.clothesstoreapp.datasource.repositoryimpl

import com.example.clothesstoreapp.datasource.model.ApiEntry
import com.example.clothesstoreapp.datasource.model.Product
import com.example.clothesstoreapp.datasource.network.api.ApiService
import com.example.clothesstoreapp.datasource.repository.Repository
import com.example.clothesstoreapp.datasource.localdatasource.AppDatabase
import com.example.clothesstoreapp.datasource.utils.BasketMapper
import com.example.clothesstoreapp.datasource.utils.WishlistMapper
import com.example.clothesstoreapp.datasource.network.utils.Resource
import com.example.clothesstoreapp.datasource.network.utils.ResponseConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

/**
 *  Actual implementation of the repository that communicates with remote source and to local database
 *  Repository acts as a single source of truth for data in our app
 */
class RepositoryImpl
@Inject constructor(
    private val apiService: ApiService,
    private val responseConverter: ResponseConverter,
    private val db: AppDatabase,
    private val wishlistMapper: WishlistMapper,
    private val basketMapper : BasketMapper

) : Repository {


    override suspend fun fetchProducts(): Resource<ApiEntry> {
        return responseConverter.responseToResult(
            apiService.fetchProducts()
        )
    }

    override suspend fun fetchWishlistItems(): List<Product> {
        return wishlistMapper.mapFromEntityList(db.WishlistDao().getAll())
    }

    override suspend fun insertWishlistItem(item: Product): Long {
        return db.WishlistDao().insertItem(wishlistMapper.mapToEntity(item))
    }
    override suspend fun deleteWishlistItem(item: Product) {
        return db.WishlistDao().deleteItem(wishlistMapper.mapToEntity(item))
    }

    /**
     * I have used flows and coroutines for applying reactive programming where the wishlist and basket badges
     * are always observed form the ROOM database and thus the UI will always be updated when removing/adding items to
     * the database table.
     */
    override suspend fun getWishListCount(): Flow<Int> {
        return db.WishlistDao().getWishListCount().transform {
            emit(it)
        }
    }

    override suspend fun getBasketCount(): Flow<Int> {
        return db.BasketDao().getBasketCount().transform {
            emit(it)
        }
    }

    override suspend fun deleteBasketItemAndFetch(productId : Int): List<Product> {
       return basketMapper.mapFromEntityList(db.BasketDao().deleteBasketEntryAndRefresh(productId))
    }

    override suspend fun fetchBasketItems(): List<Product> {
        return basketMapper.mapFromEntityList(db.BasketDao().getAllWithQty())
    }

    override suspend fun insertBasketItem(item: Product): Long {
        return db.BasketDao().insertItem(basketMapper.mapToEntity(item))
    }

}