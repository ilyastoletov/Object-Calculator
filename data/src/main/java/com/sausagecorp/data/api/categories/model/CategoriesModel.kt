package com.sausagecorp.data.api.categories.model

data class CategoriesModel(
    val _id: String,
    val name: String,
    val categoryId: Int,
    val sub_categories: List<SubCategory>,
    val products: List<Product>,
    val __v: Int
)