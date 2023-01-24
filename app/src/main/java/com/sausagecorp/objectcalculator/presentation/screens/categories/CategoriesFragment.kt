package com.sausagecorp.objectcalculator.presentation.screens.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sausagecorp.objectcalculator.databinding.FragmentCategoriesBinding
import com.sausagecorp.objectcalculator.presentation.adapters.CategoryAdapter
import com.sausagecorp.objectcalculator.presentation.models.CategoryModel

class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val categorisRv = binding.categoriesRv

        val defaultCategoriesList: ArrayList<CategoryModel> = arrayListOf(
            CategoryModel("Пол"),
            CategoryModel("Потолок"),
            CategoryModel("Обои"),
            CategoryModel("Краска")
        )

        val adapterCategories = CategoryAdapter(defaultCategoriesList)
        categorisRv.apply {
            adapter = adapterCategories
            layoutManager = LinearLayoutManager(context)
        }
    }

}