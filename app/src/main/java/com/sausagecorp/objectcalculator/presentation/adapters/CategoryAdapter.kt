package com.sausagecorp.objectcalculator.presentation.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sausagecorp.domain.models.SubCategoryModel
import com.sausagecorp.objectcalculator.R
import com.sausagecorp.objectcalculator.databinding.CategoryItemBinding
import com.sausagecorp.objectcalculator.databinding.FragmentCategoriesBinding
import com.sausagecorp.objectcalculator.presentation.screens.categories.CategoriesFragmentViewModel

class CategoryAdapter(private var categoriesList: ArrayList<SubCategoryModel>,
                      private val fragmentBinding: FragmentCategoriesBinding,
                      private val fragmentViewModel: CategoriesFragmentViewModel,
                      private val fragmentLifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<CategoryAdapter.Holder>() {

    inner class Holder(private val view: View, private val binding: CategoryItemBinding) : RecyclerView.ViewHolder(view), ItemClickListener {
        fun bind(model: SubCategoryModel) {
            binding.categoryNameCard.text = model.subCategoryName
            view.setOnClickListener {
                onClick(model)
            }
        }

        override fun onClick(model: SubCategoryModel) {
            fragmentBinding.categoryNameTV.text = model.subCategoryName
            fragmentViewModel.loadCategoriesById(model.subCategoryId)
            if (model.subCategoryName != "Товары") {
                fragmentViewModel.categoriesList.observe(fragmentLifecycleOwner) {
                    changeList(it)
                }
            } else {
                navigateToProducts(model, view)
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
    private fun navigateToProducts(model: SubCategoryModel, view: View) {
        val navigationBundle = Bundle()
        navigationBundle.putString("category_name", model.subCategoryName)
        navigationBundle.putInt("category_id", model.subCategoryId)
        view.findNavController()
            .navigate(R.id.action_categoriesFragment_to_productsFragment, navigationBundle)
    }

    interface ItemClickListener {
        fun onClick(model: SubCategoryModel)
    }

}