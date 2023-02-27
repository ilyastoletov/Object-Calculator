package com.sausagecorp.domain.usecase

import com.sausagecorp.domain.models.ProductModel
import com.sausagecorp.domain.repository.ProductsRepository

class GetProductsMainScreenUseCase(private val repository: ProductsRepository) {
    fun invoke(): List<ProductModel> = repository.getProductsForMainScreen()
}