package com.sausagecorp.objectcalculator.presentation.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sausagecorp.domain.models.CategoryModel
import com.sausagecorp.domain.models.SubCategoryModel
import com.sausagecorp.objectcalculator.R
import com.sausagecorp.objectcalculator.databinding.CategoryItemBinding
import com.sausagecorp.objectcalculator.databinding.FragmentCategoriesBinding
import com.sausagecorp.objectcalculator.presentation.screens.categories.CategoriesFragmentViewModel
import java.util.LinkedList

class CategoryAdapter(private var categoriesList: ArrayList<SubCategoryModel>, private val listener: ItemClickListener) : RecyclerView.Adapter<CategoryAdapter.Holder>() {

    inner class Holder(private val view: View, private val binding: CategoryItemBinding) : RecyclerView.ViewHolder(view) {
        fun bind(model: SubCategoryModel) {
            val name = model.subCategoryName
            binding.categoryNameCard.text = name
            view.setOnClickListener {
                listener.onClick(
                    model = model,
                    adapterInstance = this@CategoryAdapter,
                    categoryName = name
                )
            }
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

    fun changeList(newList: ArrayList<SubCategoryModel>) {
        categoriesList = newList
        notifyDataSetChanged()
    }

    // If clicked on products card, navigating to ProductsFragment
    fun navigateToProducts(model: SubCategoryModel, view: View, categoryName: String) {
        val navigationBundle = Bundle()
        navigationBundle.putString("category_name", categoryName)
        navigationBundle.putInt("category_id", model.subCategoryId)
        view.findNavController()
            .navigate(resId = R.id.action_categoriesFragment_to_productsFragment, args = navigationBundle)
    }

    interface ItemClickListener {
        fun onClick(model: SubCategoryModel, adapterInstance: CategoryAdapter, categoryName: String)
    }

}