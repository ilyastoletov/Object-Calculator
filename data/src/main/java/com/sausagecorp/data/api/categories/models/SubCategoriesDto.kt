package com.sausagecorp.data.api.categories.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SubCategoriesDto(
    @SerializedName("categoryId")
    @Expose
    val subCategoryId: Int,
    @SerializedName("name")
    @Expose
    val subCategoryName: String,
    @SerializedName("_id")
    val subCategoryRecordId: String
)
