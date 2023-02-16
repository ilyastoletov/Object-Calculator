package com.sausagecorp.domain.usecase

import com.sausagecorp.domain.models.ProductModel
import com.sausagecorp.domain.repository.ProductsRepository

class SaveProductsToSharedPrefsUseCase(private val repository: ProductsRepository) {
    fun invoke(productsList: ArrayList<ProductModel>) = repository.saveProductsToSharedPrefs(productsList)
}