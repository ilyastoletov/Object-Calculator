package com.sausagecorp.domain.repository

import com.sausagecorp.domain.models.CategoryModel
import com.sausagecorp.domain.models.SubCategoryModel

interface CategoriesRepository {
    suspend fun getCategoriesById(id: Int): ArrayList<SubCategoryModel>
}