package com.sausagecorp.objectcalculator.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sausagecorp.objectcalculator.databinding.CategoryItemBinding
import com.sausagecorp.objectcalculator.presentation.models.CategoryModel

class CategoryAdapter(val categoriesList: ArrayList<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.Holder>() {

    inner class Holder(view: View, val binding: CategoryItemBinding) : RecyclerView.ViewHolder(view) {
        fun bind(model: CategoryModel) {
            binding.categoryNameCard.text = model.categoryName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(categoriesList[position])
    }

    override fun getItemCount(): Int = categoriesList.size


}