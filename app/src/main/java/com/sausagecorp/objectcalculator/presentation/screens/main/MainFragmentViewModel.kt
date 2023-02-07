package com.sausagecorp.objectcalculator.presentation.screens.main

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import com.sausagecorp.data.repository.ObjectVolumeRepositoryImpl
import com.sausagecorp.data.repository.ProductRepositoryImpl
import com.sausagecorp.domain.models.ProductModel
import com.sausagecorp.domain.usecase.CountFullPriceUseCase
import com.sausagecorp.domain.usecase.CountObjectVolumeUseCase
import com.sausagecorp.domain.usecase.GetProductsListUseCase
import com.sausagecorp.objectcalculator.presentation.screens.products.ProductsFragmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragmentViewModel(application: Application) : ViewModel() {

    private val appContext = application.applicationContext

    private val objectVolumeRepoImpl = ObjectVolumeRepositoryImpl()
    private val productRepoImpl = ProductRepositoryImpl(appContext)

    private val countObjectVolumeUseCase = CountObjectVolumeUseCase(objectVolumeRepoImpl)
    private val getProductsListUseCase = GetProductsListUseCase(productRepoImpl)
    private val countFullPriceUseCase = CountFullPriceUseCase(objectVolumeRepoImpl)

    private val _objectVolume: MutableLiveData<Double> = MutableLiveData()
    private val _productsList: MutableLiveData<ArrayList<ProductModel>> = MutableLiveData()
    private val _fullPrice: MutableLiveData<Int> = MutableLiveData()

    val objectVolume: LiveData<Double> = _objectVolume
    val productsList: LiveData<ArrayList<ProductModel>> = _productsList
    val fullPrice: LiveData<Int> = _fullPrice

    fun countObjectVolume(a: Double, b: Double, c: Double) {
        val objectVolume = countObjectVolumeUseCase.invoke(a, b, c)
        _objectVolume.postValue(objectVolume)
    }

    fun getProductsList() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getProductsListUseCase.invoke()
            _productsList.postValue(result)
        }
    }

    fun countFullPrice() {
        val fullPrice = countFullPriceUseCase.invoke(objectVolume.value!!, productsList.value!!)
        _fullPrice.postValue(fullPrice)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                return ProductsFragmentViewModel(application) as T
            }
        }
    }

}