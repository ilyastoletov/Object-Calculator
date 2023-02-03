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
    @SerializedName("_id")
    val productRecordId: String
)
