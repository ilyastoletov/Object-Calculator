package com.sausagecorp.data.storage.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_cart")
data class ProductsCartModel(
    @PrimaryKey
    val productName: String,
    val productPrice: Int,
    val productQuantity: Int,
    val productAddedCount: Int = 0,
    val productParentCategory: String
)
