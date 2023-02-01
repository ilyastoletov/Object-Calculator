package com.sausagecorp.domain.usecase

import com.sausagecorp.data.api.categories.model.CategoriesModel
import com.sausagecorp.data.api.categories.repositories.CategoriesApiRepository

class GetCategoryByIdUseCase {
    suspend fun execute(id: Int): CategoriesModel = CategoriesApiRepository.getCategoryById(id).body()!!
}