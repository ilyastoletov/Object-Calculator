package com.sausagecorp.data.api.categories.mappers

import com.sausagecorp.data.api.categories.models.CategoriesDto
import com.sausagecorp.data.storage.database.ProductsDbModel
import com.sausagecorp.domain.models.CategoryModel
import com.sausagecorp.domain.models.ProductModel
import com.sausagecorp.domain.models.SubCategoryModel

class CategoryMapper {
    fun mapDtoToModel(dto: CategoriesDto): CategoryModel {
        val subCategoriesList: List<SubCategoryModel> = dto.subCategoriesList.map { t -> SubCategoryModel(t.subCategoryName, t.subCategoryId) }
        val productsList: List<ProductModel> = dto.productsList.map { t -> ProductModel(t.productName, t.productPrice, t.productsQuantity) }
        return CategoryModel(
            categoryName = dto.categoryName,
            categoryId = dto.categoryId,
            subCategories = subCategoriesList,
            products = productsList
        )
    }

    fun mapProductsListToDbClass(model: ArrayList<ProductModel>): ArrayList<ProductsDbModel> {
        val dbModel: ArrayList<ProductsDbModel> = arrayListOf()
        for (product in model) {
            dbModel.add(ProductsDbModel(
                productName = product.name,
                productPrice = product.price,
                productQuantity = product.quantity,
                productAddedCount = product.added
            ))
        }
        return dbModel
    }

    fun mapProductToDbClass(product: ProductModel): ProductsDbModel = ProductsDbModel(
        productName = product.name,
        productPrice = product.price,
        productQuantity = product.quantity,
        productAddedCount = product.added
    )

    fun mapDbProductsToModel(dbModel: List<ProductsDbModel>): ArrayList<ProductModel> {
        val filledModel: ArrayList<ProductModel> = arrayListOf()
        for (product in dbModel) {
            filledModel.add(ProductModel(
                name = product.productName,
                price = product.productPrice,
                quantity = product.productQuantity,
                added = product.productAddedCount
            ))
        }
        return filledModel
    }
    
}