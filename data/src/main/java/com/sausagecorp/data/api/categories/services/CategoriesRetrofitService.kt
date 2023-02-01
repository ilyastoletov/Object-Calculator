package com.sausagecorp.data.api.categories.services

import com.sausagecorp.data.api.categories.model.CategoriesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoriesRetrofitService {

    @GET("getAll")
    suspend fun getCategoriesList(): Response<CategoriesModel>

    @GET("getById")
    suspend fun getCategoryById(@Query("id") categoryId: Int): Response<CategoriesModel>
}