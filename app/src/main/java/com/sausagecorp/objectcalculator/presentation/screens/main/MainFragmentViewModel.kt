package com.sausagecorp.objectcalculator.presentation.screens.main

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import com.sausagecorp.data.repository.ObjectVolumeRepositoryImpl
import com.sausagecorp.data.repository.ProductRepositoryImpl
import com.sausagecorp.domain.models.ProductModel
import com.sausagecorp.domain.usecase.*
import com.sausagecorp.objectcalculator.presentation.screens.products.ProductsFragmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragmentViewModel(application: Application) : ViewModel() {

    private val appContext = application.applicationContext

    private val objectVolumeRepoImpl = ObjectVolumeRepositoryImpl()
    private val productRepoImpl = ProductRepositoryImpl(appContext)

    private val countObjectVolumeUseCase = CountObjectVolumeUseCase(objectVolumeRepoImpl)
    private val getProductsMainScreenUseCase = GetProductsMainScreenUseCase(productRepoImpl)
    private val countFullPriceUseCase = CountFullPriceUseCase(objectVolumeRepoImpl)
    private val deleteProductUseCase = DeleteProductUseCase(productRepoImpl)

    private val _objectVolume: MutableLiveData<Double> = MutableLiveData()
    private val _productsList: MutableLiveData<List<ProductModel>> = MutableLiveData()
    private val _fullPrice: MutableLiveData<Int> = MutableLiveData()

    val objectVolume: LiveData<Double> = _objectVolume
    val productsList: LiveData<List<ProductModel>> = _productsList
    val fullPrice: LiveData<Int> = _fullPrice

    fun countObjectVolume(a: Double, b: Double, c: Double) {
        val objectVolume = countObjectVolumeUseCase.invoke(a, b, c)
        _objectVolume.postValue(objectVolume)
    }

    fun getProductsList() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getProductsMainScreenUseCase.invoke()
            _productsList.postValue(result)
        }
    }

    fun countFullPrice(volume: Double, productsList: List<ProductModel>) {
        val fullPrice = countFullPriceUseCase.invoke(volume, productsList)
        _fullPrice.postValue(fullPrice)
    }

    fun deleteProduct(productName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteProductUseCase.invoke(productName)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                return MainFragmentViewModel(application) as T
            }
        }
    }

}