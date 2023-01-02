package com.sausagecorp.objectcalculator.presentation.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sausagecorp.domain.usecase.CountObjectVolumeUseCase

class MainFragmentViewModel : ViewModel() {

    private val _objectVolume: MutableLiveData<Double> = MutableLiveData()
    val objectVolume: LiveData<Double> = _objectVolume

    fun countObjectVolume(a: Double, b: Double, c: Double) {
        _objectVolume.postValue(CountObjectVolumeUseCase().execute(a, b, c))
    }

}