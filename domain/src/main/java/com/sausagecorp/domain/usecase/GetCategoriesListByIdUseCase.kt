package com.sausagecorp.domain.usecase


import com.sausagecorp.domain.models.SubCategoryModel
import com.sausagecorp.domain.repository.CategoriesRepository

class GetCategoriesListByIdUseCase(private val categoriesRepository: CategoriesRepository) {
    suspend fun invoke(id: Int): ArrayList<SubCategoryModel> = categoriesRepository.getCategoriesById(id)
}