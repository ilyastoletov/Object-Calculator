package com.sausagecorp.data.api.categories.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductsDto(
    @SerializedName("name")
    @Expose
    val productName: String,
    @SerializedName("price")
    @Expose
    val productPrice: Int,
    @SerializedName("quantity")
    @Expose
    val productsQuantity: Int,
    @SerializedName("parent_category")
    @Expose
    val productParentCategory: String,
    @SerializedName("_id")
    val productRecordId: String
)
