package com.sausagecorp.data.storage.database

import androidx.room.*
import com.sausagecorp.domain.models.ProductModel

@Dao
interface ProductsDao {
    @Insert(entity = ProductsDbModel::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductsList(productsList: ArrayList<ProductsDbModel>)

    @Query("SELECT * FROM products")
    fun getProductsList(): List<ProductsDbModel>

    @Query("SELECT * FROM product_cart")
    fun getProductsFromCart(): List<ProductsCartModel>

    @Insert(entity = ProductsCartModel::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProductToCart(product: ProductsCartModel)

    @Query("DELETE FROM products WHERE productName = :productName")
    suspend fun deleteProduct(productName: String)

    @Query("DELETE FROM product_cart")
    suspend fun deleteAllProductsFromCart()
}