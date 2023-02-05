package com.sausagecorp.objectcalculator.presentation.screens.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sausagecorp.data.repository.ProductRepositoryImpl
import com.sausagecorp.domain.models.ProductModel
import com.sausagecorp.domain.usecase.GetProductsListByCategoryIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsFragmentViewModel : ViewModel() {

    private val productsRepoImpl = ProductRepositoryImpl()
    private val getProductsListByCategoryIdUseCase = GetProductsListByCategoryIdUseCase(productsRepoImpl)

    private val _productsList: MutableLiveData<ArrayList<ProductModel>> = MutableLiveData()
    val productsList: LiveData<ArrayList<ProductModel>> = _productsList

    fun getProductsList(categoryId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val products = getProductsListByCategoryIdUseCase.invoke(categoryId)
            _productsList.postValue(products)
        }
    }
}