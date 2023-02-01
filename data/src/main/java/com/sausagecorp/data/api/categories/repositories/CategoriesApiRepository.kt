package com.sausagecorp.data.api.categories.repositories

import com.sausagecorp.data.api.categories.model.CategoriesModel
import com.sausagecorp.data.api.categories.services.CategoriesRetrofitInstance
import retrofit2.Response

object CategoriesApiRepository {

    suspend fun getAllCategories(): Response<CategoriesModel> = CategoriesRetrofitInstance.api.getCategoriesList()

    suspend fun getCategoryById(id: Int): Response<CategoriesModel> = CategoriesRetrofitInstance.api.getCategoryById(id)

}