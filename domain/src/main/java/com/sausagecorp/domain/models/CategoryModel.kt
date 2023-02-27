package com.sausagecorp.domain.models

data class CategoryModel(val categoryName: String,
                         val categoryId: Int,
                         val subCategories: List<SubCategoryModel>,
                         val products: List<ProductModel>
)