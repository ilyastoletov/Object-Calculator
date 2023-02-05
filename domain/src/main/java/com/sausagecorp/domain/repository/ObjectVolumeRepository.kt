package com.sausagecorp.domain.repository

interface ObjectVolumeRepository {
    fun countObjectVolume(a: Double, b: Double, c: Double): Double
}