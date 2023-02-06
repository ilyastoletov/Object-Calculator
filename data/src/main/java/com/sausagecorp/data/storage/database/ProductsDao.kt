package com.sausagecorp.data.storage.database

import androidx.room.*
import com.sausagecorp.domain.models.ProductModel

@Dao
interface ProductsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductsList(productsList: ArrayList<ProductsDbModel>)

    @Query("SELECT * FROM products")
    fun getProductsList(): List<ProductsDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProduct(product: ProductsDbModel)
}