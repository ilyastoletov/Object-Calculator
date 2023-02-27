package com.sausagecorp.objectcalculator.presentation.screens.products

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import com.sausagecorp.data.repository.ProductRepositoryImpl
import com.sausagecorp.domain.models.ProductModel
import com.sausagecorp.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsFragmentViewModel(application: Application) : ViewModel() {

    private val appContext: Context = application.applicationContext

    private val productsRepoImpl = ProductRepositoryImpl(appContext)

    private val getProductsListByCategoryIdUseCase = GetProductsListByCategoryIdUseCase(productsRepoImpl)
    private val saveProductsListUseCase = SaveProductsListUseCase(productsRepoImpl)
    private val getProductsFromCartUseCase = GetProductsFromCartUseCase(productsRepoImpl)
    private val saveProductUseCase = SaveProductUseCase(productsRepoImpl)
    private val wipeCartUseCase = WipeCartUseCase(productsRepoImpl)
    private val getProductsFromSharedPrefsUseCase = LoadProductsFromSharedPrefsUseCase(productsRepoImpl)
    private val saveProductsToSharedPrefsUseCase = SaveProductsToSharedPrefsUseCase(productsRepoImpl)


    private val _productsList: MutableLiveData<ArrayList<ProductModel>> = MutableLiveData()
    val productsList: LiveData<ArrayList<ProductModel>> = _productsList


    fun getProductsListByCategoryId(categoryId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val productsFromApi = getProductsListByCategoryIdUseCase.invoke(categoryId)
            _productsList.postValue(productsFromApi)
        }
    }

    fun getProductsListFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getProductsFromCartUseCase.invoke()
            _productsList.postValue(result)
        }
    }

    fun saveProductsList(productsList: ArrayList<ProductModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            saveProductsListUseCase.invoke(productsList)
        }
    }

    fun saveProduct(product: ProductModel) {
        viewModelScope.launch(Dispatchers.IO) {
            saveProductUseCase.invoke(product)
        }
    }

    fun wipeCart() {
        viewModelScope.launch(Dispatchers.IO) {
            wipeCartUseCase.invoke()
        }
    }

    fun saveProductsToSharedPrefs(productsList: ArrayList<ProductModel>) {
        saveProductsToSharedPrefsUseCase.invoke(productsList)
    }

    fun getProductsFromSharedPrefs(): ArrayList<ProductModel> {
        return getProductsFromSharedPrefsUseCase.invoke()
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