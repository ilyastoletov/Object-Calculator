package com.sausagecorp.data.repository

import com.sausagecorp.domain.repository.ObjectVolumeRepository

class ObjectVolumeRepositoryImpl : ObjectVolumeRepository{
    override fun countObjectVolume(a: Double, b: Double, c: Double): Double = a * b * c
}