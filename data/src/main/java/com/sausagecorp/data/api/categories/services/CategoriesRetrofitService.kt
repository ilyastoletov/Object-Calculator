package com.sausagecorp.data.api.categories.services

import com.sausagecorp.data.api.categories.models.CategoriesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoriesRetrofitService {
    @GET("getById")
    suspend fun getCategoryById(@Query("id") categoryId: Int): CategoriesDto

}