package com.sausagecorp.objectcalculator.presentation.screens.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sausagecorp.domain.models.SubCategoryModel
import com.sausagecorp.objectcalculator.databinding.FragmentCategoriesBinding
import com.sausagecorp.objectcalculator.presentation.adapters.CategoryAdapter

class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding
    private val viewModel by viewModels<CategoriesFragmentViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Loading default category list to RecyclerView when fragment launched
        val defaultCategoriesList: ArrayList<SubCategoryModel> = arrayListOf(
                SubCategoryModel("Пол", 1),
                SubCategoryModel("Потолок", 0),
                SubCategoryModel("Обои", 0),
                SubCategoryModel("Краска", 0)
        )
        initRv(defaultCategoriesList)
    }

    // Initializing category RecyclerView
    private fun initRv(categoriesList: ArrayList<SubCategoryModel>) {
        val categorisRv = binding.categoriesRv
        val adapterCategories = CategoryAdapter(categoriesList, binding, viewModel, viewLifecycleOwner)
        categorisRv.apply {
            adapter = adapterCategories
            layoutManager = LinearLayoutManager(context)
        }
    }

}