package com.sausagecorp.domain.usecase

import com.sausagecorp.domain.repository.ProductsRepository

class WipeCartUseCase(private val repository: ProductsRepository) {
    suspend fun invoke() = repository.wipeCart()
}