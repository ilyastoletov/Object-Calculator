package com.sausagecorp.domain.repository

import com.sausagecorp.domain.models.ProductModel

interface ProductsRepository {
    suspend fun getProductsByCategoryId(id: Int): ArrayList<ProductModel>
    suspend fun saveProductsList(productsList: ArrayList<ProductModel>)
    fun getProductsList(): ArrayList<ProductModel>
    suspend fun saveProduct(product: ProductModel)
}