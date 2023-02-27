package com.sausagecorp.domain.repository

import com.sausagecorp.domain.models.ProductModel

interface ProductsRepository {
    suspend fun getProductsByCategoryId(id: Int): ArrayList<ProductModel>
    suspend fun saveProductsList(productsList: ArrayList<ProductModel>)
    fun getProductsList(): ArrayList<ProductModel>
    suspend fun saveProduct(product: ProductModel)
    suspend fun wipeCart()
    fun saveProductsToSharedPrefs(productsList: ArrayList<ProductModel>)
    fun getProductsFromSharedPrefs(): ArrayList<ProductModel>
    fun getProductsFromCart(): ArrayList<ProductModel>
    fun getProductsForMainScreen(): List<ProductModel>
    suspend fun deleteProduct(productName: String)

}