package com.sausagecorp.objectcalculator.presentation.screens.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sausagecorp.data.repository.CategoriesRepositoryImpl
import com.sausagecorp.domain.models.SubCategoryModel
import com.sausagecorp.domain.usecase.GetCategoriesListByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriesFragmentViewModel : ViewModel() {

    private val categoriesRepoImpl = CategoriesRepositoryImpl()

    private val getCategoriesListByIdUseCase = GetCategoriesListByIdUseCase(categoriesRepoImpl)

    private val _categoriesList: MutableLiveData<ArrayList<SubCategoryModel>> = MutableLiveData()
    val categoriesList: LiveData<ArrayList<SubCategoryModel>> = _categoriesList

    fun loadCategoriesById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val subCategoriesList = getCategoriesListByIdUseCase.invoke(id)
            _categoriesList.postValue(subCategoriesList)
        }
    }

}