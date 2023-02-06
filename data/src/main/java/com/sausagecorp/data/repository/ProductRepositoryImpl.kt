package com.sausagecorp.data.repository

import android.content.Context
import com.sausagecorp.data.api.categories.services.CategoriesRetrofitInstance
import com.sausagecorp.data.api.categories.mappers.CategoryMapper
import com.sausagecorp.data.storage.database.ProductsDatabase
import com.sausagecorp.domain.models.ProductModel
import com.sausagecorp.domain.repository.ProductsRepository

class ProductRepositoryImpl(context: Context) : ProductsRepository {

    private val apiService = CategoriesRetrofitInstance.api
    private val mapper = CategoryMapper()

    private val databaseInstance = ProductsDatabase.getDatabaseInstance(context)
    private val productsDao = databaseInstance.getProductsDao()

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

    override fun getProductsList(): ArrayList<ProductModel> {
        val productsList = productsDao.getProductsList()
        return mapper.mapDbProductsToModel(productsList)
    }

    override suspend fun saveProductsList(productsList: ArrayList<ProductModel>) {
        val mappedModel = mapper.mapProductsListToDbClass(productsList)
        productsDao.insertProductsList(mappedModel)
    }

    override suspend fun saveProduct(product: ProductModel) {
        val mappedProduct = mapper.mapProductToDbClass(product)
        productsDao.saveProduct(mappedProduct)
    }

}