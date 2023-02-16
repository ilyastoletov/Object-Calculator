package com.sausagecorp.data.repository

import android.content.Context
import com.google.gson.Gson
import com.sausagecorp.data.api.categories.services.CategoriesRetrofitInstance
import com.sausagecorp.data.api.categories.mappers.CategoryMapper
import com.sausagecorp.data.api.categories.mappers.ProductMapper
import com.sausagecorp.data.storage.database.ProductsDatabase
import com.sausagecorp.domain.models.ProductModel
import com.sausagecorp.domain.repository.ProductsRepository


private const val SHARED_PREFS_NAME = "main"
private const val SHARED_PREFS_KEY = "products"

class ProductRepositoryImpl(context: Context) : ProductsRepository {

    private val apiService = CategoriesRetrofitInstance.api

    private val productMapper = ProductMapper()
    private val categoryMapper = CategoryMapper()

    private val databaseInstance = ProductsDatabase.getDatabaseInstance(context)
    private val productsDao = databaseInstance.getProductsDao()

    private val sharedPrefs = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    private val gsonDecoder = Gson()

    override suspend fun getProductsByCategoryId(id: Int): ArrayList<ProductModel> {
        val categoriesResponse = apiService.getCategoryById(id)
        val categoryMappedModel = categoryMapper.mapDtoToModel(categoriesResponse)
        val productsList: ArrayList<ProductModel> = arrayListOf()
        for (product in categoryMappedModel.products) {
            productsList.add(ProductModel(
                name = product.name,
                price = product.price,
                quantity = product.quantity,
                added = product.added,
                parentCategory = product.parentCategory
            ))
        }
        return productsList
    }

    override fun getProductsList(): ArrayList<ProductModel> {
        val productsList = productsDao.getProductsList()
        return productMapper.mapDbProductsToModel(productsList)
    }

    override fun getProductsForMainScreen(): List<ProductModel> {
        val oldProductsList = getProductsList()
        println(oldProductsList)
        return oldProductsList.filter { it.added > 0 }
    }

    override suspend fun saveProductsList(productsList: ArrayList<ProductModel>) {
        val mappedModel = productMapper.mapProductsListToDbClass(productsList)
        productsDao.insertProductsList(mappedModel)
    }

    override suspend fun saveProduct(product: ProductModel) {
        val mappedProduct = productMapper.mapProductToCart(product)
        productsDao.saveProductToCart(product = mappedProduct)
    }

    override suspend fun deleteProduct(productName: String) {
        productsDao.deleteProduct(productName)
    }

    override fun getProductsFromCart(): ArrayList<ProductModel> {
        val cartList = productsDao.getProductsFromCart()
        return productMapper.mapCartToList(cartList)
    }

    override suspend fun wipeCart() {
        productsDao.deleteAllProductsFromCart()
    }

    override fun getProductsFromSharedPrefs(): ArrayList<ProductModel> {
        val sharedPrefsProducts = sharedPrefs.getString(SHARED_PREFS_KEY, "")!!
        return gsonDecoder.fromJson(sharedPrefsProducts, Array<ProductModel>::class.java)
            .toCollection(ArrayList())
    }

    override fun saveProductsToSharedPrefs(productsList: ArrayList<ProductModel>) {
        val encodedModel = gsonDecoder.toJson(productsList)
        sharedPrefs.edit().putString(SHARED_PREFS_KEY, encodedModel).commit()
    }

}