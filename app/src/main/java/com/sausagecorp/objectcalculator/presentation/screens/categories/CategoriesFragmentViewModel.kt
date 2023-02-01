package com.sausagecorp.objectcalculator.presentation.screens.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sausagecorp.data.api.categories.model.CategoriesModel
import com.sausagecorp.domain.usecase.GetAllCategoriesUseCase
import com.sausagecorp.domain.usecase.GetCategoryByIdUseCase
import com.sausagecorp.objectcalculator.presentation.models.CategoryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriesFragmentViewModel : ViewModel() {

    val _categoriesList: MutableLiveData<ArrayList<CategoryModel>> = MutableLiveData()
    val categoriesList: LiveData<ArrayList<CategoryModel>> = _categoriesList

    fun loadCategoriesById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val categoryResponse: CategoriesModel = GetCategoryByIdUseCase().execute(id)
            val categoriesList: ArrayList<CategoryModel> = arrayListOf()
            for (cat in categoryResponse.sub_categories) {
                categoriesList.add(CategoryModel(cat.name, cat.categoryId))
            }
            if (categoryResponse.products.isNotEmpty()) {
                categoriesList.add(CategoryModel("Товары", categoryResponse.categoryId))
            }
            _categoriesList.postValue(categoriesList)
        }
    }

}