package com.sausagecorp.domain.usecase

import com.sausagecorp.domain.models.ProductModel
import com.sausagecorp.domain.repository.ObjectVolumeRepository

class CountFullPriceUseCase(private val repository: ObjectVolumeRepository) {
    fun invoke(volume: Double, productsList: List<ProductModel>): Int = repository.countProductsCost(volume, productsList)
}