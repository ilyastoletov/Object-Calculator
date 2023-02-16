package com.sausagecorp.domain.usecase

import com.sausagecorp.domain.models.ProductModel
import com.sausagecorp.domain.repository.ProductsRepository

class LoadProductsFromSharedPrefsUseCase(private val repository: ProductsRepository) {
    fun invoke(): ArrayList<ProductModel> = repository.getProductsFromSharedPrefs()
}