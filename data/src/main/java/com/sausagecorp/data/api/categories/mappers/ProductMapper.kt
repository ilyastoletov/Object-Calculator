package com.sausagecorp.data.api.categories.mappers

import com.sausagecorp.data.storage.database.ProductsCartModel
import com.sausagecorp.data.storage.database.ProductsDbModel
import com.sausagecorp.domain.models.ProductModel

class ProductMapper {
    fun mapProductsListToDbClass(model: ArrayList<ProductModel>): ArrayList<ProductsDbModel> {
        val dbModel: ArrayList<ProductsDbModel> = arrayListOf()
        for (product in model) {
            dbModel.add(
                ProductsDbModel(
                productName = product.name,
                productPrice = product.price,
                productQuantity = product.quantity,
                productAddedCount = product.added,
                productParentCategory = product.parentCategory
            )
            )
        }
        return dbModel
    }

    fun mapDbProductsToModel(dbModel: List<ProductsDbModel>): ArrayList<ProductModel> {
        val filledModel: ArrayList<ProductModel> = arrayListOf()
        for (product in dbModel) {
            filledModel.add(
                ProductModel(
                name = product.productName,
                price = product.productPrice,
                quantity = product.productQuantity,
                added = product.productAddedCount,
                parentCategory = product.productParentCategory
            )
            )
        }
        return filledModel
    }

    fun mapProductToCart(product: ProductModel): ProductsCartModel = ProductsCartModel(
        productName = product.name,
        productPrice = product.price,
        productQuantity = product.quantity,
        productAddedCount = product.added,
        productParentCategory = product.parentCategory
    )

    fun mapCartToList(cartList: List<ProductsCartModel>): ArrayList<ProductModel> {
        val prodModel: ArrayList<ProductModel> = arrayListOf()
        for (product in cartList) {
            prodModel.add(
                ProductModel(
                    name = product.productName,
                    price = product.productPrice,
                    quantity = product.productQuantity,
                    added = product.productAddedCount,
                    parentCategory = product.productParentCategory
                )
            )
        }
        return prodModel
    }

}