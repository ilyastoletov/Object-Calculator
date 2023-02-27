package com.sausagecorp.data.api.categories.mappers

import com.sausagecorp.data.api.categories.models.CategoriesDto
import com.sausagecorp.data.storage.database.ProductsDbModel
import com.sausagecorp.domain.models.CategoryModel
import com.sausagecorp.domain.models.ProductModel
import com.sausagecorp.domain.models.SubCategoryModel

class CategoryMapper {
    fun mapDtoToModel(dto: CategoriesDto): CategoryModel {
        val subCategoriesList: List<SubCategoryModel> = dto.subCategoriesList.map { t -> SubCategoryModel(t.subCategoryName, t.subCategoryId) }
        val productsList: List<ProductModel> = dto.productsList.map { t -> ProductModel(t.productName, t.productPrice, t.productsQuantity, 0, t.productParentCategory) }
        return CategoryModel(
            categoryName = dto.categoryName,
            categoryId = dto.categoryId,
            subCategories = subCategoriesList,
            products = productsList
        )
    }
}