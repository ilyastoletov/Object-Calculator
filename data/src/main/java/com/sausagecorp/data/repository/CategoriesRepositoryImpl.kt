package com.sausagecorp.data.repository

import com.sausagecorp.data.api.categories.models.CategoriesDto
import com.sausagecorp.data.api.categories.services.CategoriesRetrofitInstance
import com.sausagecorp.data.mappers.CategoryMapper
import com.sausagecorp.domain.models.CategoryModel
import com.sausagecorp.domain.repository.CategoriesRepository

class CategoriesRepositoryImpl : CategoriesRepository {

    private val apiService = CategoriesRetrofitInstance.api
    private val mapper = CategoryMapper()

    override suspend fun getCategoriesById(id: Int): CategoryModel {
        val categoriesResponse: CategoriesDto = apiService.getCategoryById(id)
        return mapper.mapDtoToModel(categoriesResponse)
    }

}