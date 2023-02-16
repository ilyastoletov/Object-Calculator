package com.sausagecorp.domain.models

data class ProductModel(val name: String, val price: Int, val quantity: Int, var added: Int = 0, val parentCategory: String)