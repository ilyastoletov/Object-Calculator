package com.sausagecorp.objectcalculator.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sausagecorp.domain.models.ProductModel
import com.sausagecorp.objectcalculator.databinding.ProductMainScreenItemBinding

class ProductsMainScreenAdapter(private val productsList: ArrayList<ProductModel>) : RecyclerView.Adapter<ProductsMainScreenAdapter.ProductHolder>() {

    inner class ProductHolder(v: View, binding: ProductMainScreenItemBinding) : RecyclerView.ViewHolder(v) {
        val categoryName = binding.categoryNameText
        val productName = binding.procuctName
        val price = binding.productPrice
        val count = binding.productsCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = ProductMainScreenItemBinding.inflate(LayoutInflater.from(parent.context))
        return ProductHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.categoryName.text = "Default" //productsList[position].
        holder.productName.text = "Default" //productsList[position].productName
        holder.price.text = "Default" //productsList[position].productPrice.toString() + "₽ на м³"
        holder.count.text = "Default" //"Количество: " + productsList[position].productsCount.toString()
    }

    override fun getItemCount(): Int = productsList.size
}