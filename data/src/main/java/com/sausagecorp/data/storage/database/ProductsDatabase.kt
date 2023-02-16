package com.sausagecorp.data.storage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ProductsDbModel::class, ProductsCartModel::class], version = 7, exportSchema = false)
abstract class ProductsDatabase : RoomDatabase() {

    abstract fun getProductsDao(): ProductsDao

    companion object {
        private var productsDatabase: ProductsDatabase ?= null

        @Synchronized
        fun getDatabaseInstance(context: Context): ProductsDatabase = if(productsDatabase == null) {
            productsDatabase = Room.databaseBuilder(context, ProductsDatabase::class.java, "products_db").fallbackToDestructiveMigration().build()
            productsDatabase as ProductsDatabase
        } else {
            productsDatabase as ProductsDatabase
        }
    }
}