package com.sausagecorp.domain.repository

import com.sausagecorp.domain.models.ProductModel

interface ObjectVolumeRepository {
    fun countObjectVolume(a: Double, b: Double, c: Double): Double
    fun countProductsCost(volume: Double, productsList: ArrayList<ProductModel>): Int
}