package com.sausagecorp.domain.usecase

import com.sausagecorp.domain.models.ProductModel
import com.sausagecorp.domain.repository.ProductsRepository

class GetProductsListByCategoryIdUseCase(private val repository: ProductsRepository) {
    suspend fun invoke(id: Int): ArrayList<ProductModel> = repository.getProductsByCategoryId(id)
}