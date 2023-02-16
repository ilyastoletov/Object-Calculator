package com.sausagecorp.domain.usecase

import com.sausagecorp.domain.repository.ProductsRepository

class DeleteProductUseCase(private val repository: ProductsRepository) {
    suspend fun invoke(productName: String) = repository.deleteProduct(productName)
}