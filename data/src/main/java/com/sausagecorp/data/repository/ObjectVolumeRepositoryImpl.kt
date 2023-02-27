package com.sausagecorp.data.repository

import com.sausagecorp.domain.models.ProductModel
import com.sausagecorp.domain.repository.ObjectVolumeRepository
import kotlin.math.roundToInt

class ObjectVolumeRepositoryImpl : ObjectVolumeRepository{

    override fun countObjectVolume(a: Double, b: Double, c: Double): Double = a * b * c

    override fun countProductsCost(volume: Double, productsList: List<ProductModel>): Int {
        var productsSum = 0
        for (product in productsList) {
            productsSum += product.price * product.added
        }
        return (volume * productsSum).roundToInt()
    }

}