package com.sausagecorp.objectcalculator.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sausagecorp.domain.models.ProductModel
import com.sausagecorp.objectcalculator.databinding.ProductMainScreenItemBinding

class ProductsMainScreenAdapter(private var productsList: List<ProductModel>,
                                private val deleteClickListener: DeleteItemListener) : RecyclerView.Adapter<ProductsMainScreenAdapter.ProductHolder>() {

    inner class ProductHolder(v: View, private val binding: ProductMainScreenItemBinding) : RecyclerView.ViewHolder(v) {
        fun bind(model: ProductModel) {
            with(binding) {
                categoryNameText.text = model.parentCategory
                procuctName.text = model.name
                productPrice.text = model.price.toString() + "₽ на м³"
                productsCount.text = "Количество: " + model.added.toString()
                deleteItemButton.setOnClickListener {
                    deleteClickListener.onClick(model, this@ProductsMainScreenAdapter)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = ProductMainScreenItemBinding.inflate(LayoutInflater.from(parent.context))
        return ProductHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bind(productsList[position])
    }

    fun changeList(newList: List<ProductModel>) {
        productsList = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = productsList.size

    interface DeleteItemListener {
        fun onClick(model: ProductModel, adapterInstance: ProductsMainScreenAdapter)
    }
}