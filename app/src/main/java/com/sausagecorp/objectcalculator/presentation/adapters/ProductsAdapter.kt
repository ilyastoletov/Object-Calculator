package com.sausagecorp.objectcalculator.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sausagecorp.objectcalculator.databinding.ProductItemBinding
import com.sausagecorp.objectcalculator.presentation.models.ProductsModel

class ProductsAdapter(val productsList: ArrayList<ProductsModel>) : RecyclerView.Adapter<ProductsAdapter.ProductHolder>() {

    inner class ProductHolder(v: View, binding: ProductItemBinding) : RecyclerView.ViewHolder(v) {
        val categoryName = binding.categoryNameText
        val productName = binding.procuctName
        val price = binding.productPrice
        val count = binding.productsCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context))
        return ProductHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.categoryName.text = productsList[position].categoryName
        holder.productName.text = productsList[position].productName
        holder.price.text = productsList[position].productPrice.toString() + "₽ на м³"
        holder.count.text = "Количество: " + productsList[position].productsCount.toString()
    }

    override fun getItemCount(): Int = productsList.size
}