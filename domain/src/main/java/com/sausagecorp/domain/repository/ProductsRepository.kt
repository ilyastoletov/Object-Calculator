package com.sausagecorp.domain.repository

import com.sausagecorp.domain.models.ProductModel

interface ProductsRepository {
    suspend fun getProductsByCategoryId(id: Int): ArrayList<ProductModel>
}