package com.example.clothesstoreapp.datasource.utils

import com.example.clothesstoreapp.datasource.localdatasource.BasketItem
import com.example.clothesstoreapp.datasource.localdatasource.WishlistItem
import com.example.clothesstoreapp.datasource.model.Product
import javax.inject.Inject

class WishlistMapper
@Inject
constructor() : EntityMapper<WishlistItem, Product> {

    override fun mapFromLocalEntity(entity: WishlistItem): Product {
        return Product(
            productId = entity.productId,
            image = entity.image,
            name = entity.name,
            price = entity.price,
        )
    }

    override fun mapToEntity(domainModel: Product): WishlistItem {
        return WishlistItem(
            productId = domainModel.productId,
            image = domainModel.image,
            name = domainModel.name,
            price = domainModel.price,
        )
    }

    fun mapFromEntityList(entities: List<WishlistItem>): List<Product> {
        return entities.map { mapFromLocalEntity(it) }
    }
}

class BasketMapper
@Inject
constructor() : EntityMapper<BasketItem, Product> {

    override fun mapFromLocalEntity(entity: BasketItem): Product {
        return Product(
            productId = entity.productId,
            image = entity.image,
            name = entity.name,
            price = entity.price,
        )
    }

    override fun mapToEntity(domainModel: Product): BasketItem {
        return BasketItem(
            productId = domainModel.productId,
            image = domainModel.image,
            name = domainModel.name,
            price = domainModel.price,
        )
    }

    fun mapFromEntityList(entities: List<BasketItem>): List<Product> {
        return entities.map { mapFromLocalEntity(it) }
    }
}





















