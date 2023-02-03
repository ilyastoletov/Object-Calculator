package com.sausagecorp.data.api.categories.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CategoriesDto(
    @SerializedName("_id")
    val idRecord: String,
    @SerializedName("name")
    @Expose
    val categoryName: String,
    @SerializedName("categoryId")
    @Expose
    val categoryId: Int,
    @SerializedName("sub_categories")
    @Expose
    val subCategoriesList: List<SubCategoriesDto>,
    @SerializedName("products")
    @Expose
    val productsList: List<ProductsDto>,
    @SerializedName("__v")
    val someValue: Int
)
