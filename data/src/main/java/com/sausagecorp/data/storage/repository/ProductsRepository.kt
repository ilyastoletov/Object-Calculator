package com.sausagecorp.data.storage.repository

import com.sausagecorp.domain.models.ProductModel

interface ProductsRepository {
    suspend fun insertProductsList(productsList: ArrayList<ProductModel>)
    fun getProductsList(): ArrayList<ProductModel>
}