package com.sausagecorp.domain.repository

import com.sausagecorp.domain.models.CategoryModel

interface CategoriesRepository {
    suspend fun getCategoriesById(id: Int): CategoryModel
}