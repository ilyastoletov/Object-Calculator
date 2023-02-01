package com.sausagecorp.domain.usecase

import com.sausagecorp.data.api.categories.model.CategoriesModel
import com.sausagecorp.data.api.categories.repositories.CategoriesApiRepository


class GetAllCategoriesUseCase {

    suspend fun execute(): CategoriesModel {
        val response = CategoriesApiRepository.getAllCategories()
        return response.body()!!
    }

}