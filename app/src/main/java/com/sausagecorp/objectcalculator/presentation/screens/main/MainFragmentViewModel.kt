package com.sausagecorp.objectcalculator.presentation.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sausagecorp.data.repository.ObjectVolumeRepositoryImpl
import com.sausagecorp.domain.usecase.CountObjectVolumeUseCase

class MainFragmentViewModel : ViewModel() {

    private val objectVolumeRepoImpl = ObjectVolumeRepositoryImpl()
    private val countObjectVolumeUseCase = CountObjectVolumeUseCase(objectVolumeRepoImpl)

    private val _objectVolume: MutableLiveData<Double> = MutableLiveData()
    val objectVolume: LiveData<Double> = _objectVolume

    fun countObjectVolume(a: Double, b: Double, c: Double) {
        val objectVolume = countObjectVolumeUseCase.invoke(a, b, c)
        _objectVolume.postValue(objectVolume)
    }

}