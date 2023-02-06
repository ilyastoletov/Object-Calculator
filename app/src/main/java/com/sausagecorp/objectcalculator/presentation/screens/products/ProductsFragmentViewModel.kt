package com.sausagecorp.objectcalculator.presentation.screens.products

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sausagecorp.data.repository.ProductRepositoryImpl
import com.sausagecorp.domain.models.ProductModel
import com.sausagecorp.domain.usecase.GetProductsListByCategoryIdUseCase
import com.sausagecorp.domain.usecase.GetProductsListUseCase
import com.sausagecorp.domain.usecase.SaveProductUseCase
import com.sausagecorp.domain.usecase.SaveProductsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val appContext: Context = application.applicationContext

    private val productsRepoImpl = ProductRepositoryImpl(appContext)

    private val getProductsListByCategoryIdUseCase = GetProductsListByCategoryIdUseCase(productsRepoImpl)
    private val saveProductsListUseCase = SaveProductsListUseCase(productsRepoImpl)
    private val getProductsListUseCase = GetProductsListUseCase(productsRepoImpl)
    private val saveProductUseCase = SaveProductUseCase(productsRepoImpl)

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
            val productsList = getProductsListUseCase.invoke()
            _productsList.postValue(productsList)
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

}