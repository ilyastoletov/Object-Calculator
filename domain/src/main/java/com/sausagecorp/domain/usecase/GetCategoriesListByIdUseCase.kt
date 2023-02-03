package com.sausagecorp.domain.usecase


import com.sausagecorp.domain.models.SubCategoryModel
import com.sausagecorp.domain.repository.CategoriesRepository

class GetCategoriesListByIdUseCase(private val categoriesRepository: CategoriesRepository) {
    suspend fun invoke(id: Int): ArrayList<SubCategoryModel> {
        val categoryModel = categoriesRepository.getCategoriesById(id)
        val subCategoriesArrayList: ArrayList<SubCategoryModel> = arrayListOf()
        for (obj in categoryModel.subCategories) {
            subCategoriesArrayList.add(SubCategoryModel(obj.subCategoryName, obj.subCategoryId))
        }
        if (categoryModel.products.isNotEmpty()) {
            subCategoriesArrayList.add(SubCategoryModel("Товары", categoryModel.categoryId))
        }
        return subCategoriesArrayList
    }
}