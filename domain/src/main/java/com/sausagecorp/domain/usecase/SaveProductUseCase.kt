package com.sausagecorp.domain.usecase

import com.sausagecorp.domain.models.ProductModel
import com.sausagecorp.domain.repository.ProductsRepository

class SaveProductUseCase(private val repository: ProductsRepository) {
    suspend fun invoke(product: ProductModel) = repository.saveProduct(product)
}