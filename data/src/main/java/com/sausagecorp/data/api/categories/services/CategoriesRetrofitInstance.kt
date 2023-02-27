package com.sausagecorp.data.api.categories.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CategoriesRetrofitInstance {
    val api = Retrofit.Builder()
        .baseUrl("http://192.168.1.37:5000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CategoriesRetrofitService::class.java)
}