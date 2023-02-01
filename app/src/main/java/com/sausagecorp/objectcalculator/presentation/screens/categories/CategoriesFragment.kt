package com.sausagecorp.objectcalculator.presentation.screens.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sausagecorp.objectcalculator.databinding.FragmentCategoriesBinding
import com.sausagecorp.objectcalculator.presentation.adapters.CategoryAdapter
import com.sausagecorp.objectcalculator.presentation.models.CategoryModel

class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding
    private val viewModel by lazy { ViewModelProvider(this).get(CategoriesFragmentViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Loading default category list to RecyclerView when fragment launched
        val defaultCategoriesList: ArrayList<CategoryModel> = arrayListOf(
                CategoryModel("Пол", 1),
                CategoryModel("Потолок", 0),
                CategoryModel("Обои", 0),
                CategoryModel("Краска", 0)
        )
        initRv(defaultCategoriesList)
    }

    // Initializing category RecyclerView
    private fun initRv(categoriesList: ArrayList<CategoryModel>) {
        val categorisRv = binding.categoriesRv
        val adapterCategories = CategoryAdapter(categoriesList, binding, viewModel, viewLifecycleOwner)
        categorisRv.apply {
            adapter = adapterCategories
            layoutManager = LinearLayoutManager(context)
        }
    }

}