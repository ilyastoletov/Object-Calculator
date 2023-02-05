package com.sausagecorp.data.repository

import com.sausagecorp.data.api.categories.services.CategoriesRetrofitInstance
import com.sausagecorp.data.mappers.CategoryMapper
import com.sausagecorp.domain.models.ProductModel
import com.sausagecorp.domain.repository.ProductsRepository

class ProductRepositoryImpl : ProductsRepository {

    private val apiService = CategoriesRetrofitInstance.api
    private val mapper = CategoryMapper()

    override suspend fun getProductsByCategoryId(id: Int): ArrayList<ProductModel> {
        val categoriesResponse = apiService.getCategoryById(id)
        val categoryMappedModel = mapper.mapDtoToModel(categoriesResponse)
        val productsList: ArrayList<ProductModel> = arrayListOf()
        for (product in categoryMappedModel.products) {
            productsList.add(ProductModel(
                name = product.name,
                price = product.price,
                quantity = product.quantity
            ))
        }
        return productsList
    }
}