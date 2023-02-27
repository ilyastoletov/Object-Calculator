package com.sausagecorp.domain.usecase

import com.sausagecorp.domain.repository.ObjectVolumeRepository

class CountObjectVolumeUseCase(private val objectVolumeRepository: ObjectVolumeRepository) {
    fun invoke(a: Double, b: Double, c: Double): Double = objectVolumeRepository.countObjectVolume(a, b, c)
}