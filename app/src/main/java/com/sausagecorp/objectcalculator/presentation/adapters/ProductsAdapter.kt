package com.sausagecorp.objectcalculator.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sausagecorp.domain.models.ProductModel
import com.sausagecorp.objectcalculator.databinding.ProductsItemBinding

class ProductsAdapter(private val productsList: ArrayList<ProductModel>, private val listener: ProductClickListener) : RecyclerView.Adapter<ProductsAdapter.Holder>() {

    inner class Holder(view: View, private val binding: ProductsItemBinding) : RecyclerView.ViewHolder(view) {
        fun bind(model: ProductModel) {
            binding.apply {
                productNameTextView.text = model.name
                productQuantityTextView.text = "В наличии: " + model.quantity.toString()
                productAddedCountTextView.text = model.added.toString()
                productPriceTextView.text = "Цена: " + model.price.toString() + "₽/м³"
                addedProductsCounterTV.text = model.added.toString()

                productCountIncreaseBtn.setOnClickListener {
                    listener.onIncreaseCounter(this, model)
                }

                productCountDecreaseBtn.setOnClickListener {
                    listener.onDecreaseCounter(this, model)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ProductsItemBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(productsList[position])
    }

    override fun getItemCount(): Int = productsList.size

    interface ProductClickListener {
        fun onIncreaseCounter(binding: ProductsItemBinding, model: ProductModel)
        fun onDecreaseCounter(binding: ProductsItemBinding, model: ProductModel)
    }

}