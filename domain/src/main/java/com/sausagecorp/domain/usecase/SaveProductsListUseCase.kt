package com.sausagecorp.domain.usecase

import com.sausagecorp.domain.models.ProductModel
import com.sausagecorp.domain.repository.ProductsRepository

class SaveProductsListUseCase(private val repository: ProductsRepository) {
    suspend fun invoke(prodList: ArrayList<ProductModel>) = repository.saveProductsList(prodList)
}