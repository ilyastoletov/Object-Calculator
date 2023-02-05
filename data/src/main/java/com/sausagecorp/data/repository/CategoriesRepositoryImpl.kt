package com.sausagecorp.data.repository

import com.sausagecorp.data.api.categories.models.CategoriesDto
import com.sausagecorp.data.api.categories.services.CategoriesRetrofitInstance
import com.sausagecorp.data.mappers.CategoryMapper
import com.sausagecorp.domain.models.CategoryModel
import com.sausagecorp.domain.models.SubCategoryModel
import com.sausagecorp.domain.repository.CategoriesRepository

class CategoriesRepositoryImpl : CategoriesRepository {

    private val apiService = CategoriesRetrofitInstance.api
    private val mapper = CategoryMapper()

    override suspend fun getCategoriesById(id: Int): ArrayList<SubCategoryModel> {
        val categoriesResponse: CategoriesDto = apiService.getCategoryById(id)
        val categoriesModel = mapper.mapDtoToModel(categoriesResponse)
        val subCategoriesArrayList: ArrayList<SubCategoryModel> = arrayListOf()
        for (obj in categoriesModel.subCategories) {
            subCategoriesArrayList.add(SubCategoryModel(obj.subCategoryName, obj.subCategoryId))
        }
        if (categoriesModel.products.isNotEmpty()) {
            subCategoriesArrayList.add(SubCategoryModel("Товары", categoriesModel.categoryId))
        }
        return subCategoriesArrayList
    }

}