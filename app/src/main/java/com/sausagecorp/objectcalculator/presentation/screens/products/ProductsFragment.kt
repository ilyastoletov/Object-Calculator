package com.sausagecorp.objectcalculator.presentation.screens.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sausagecorp.domain.models.ProductModel
import com.sausagecorp.objectcalculator.R
import com.sausagecorp.objectcalculator.databinding.FragmentProductsBinding
import com.sausagecorp.objectcalculator.databinding.ProductsItemBinding
import com.sausagecorp.objectcalculator.presentation.adapters.ProductsAdapter

class ProductsFragment : Fragment() {

    private lateinit var binding: FragmentProductsBinding
    private val viewModel by viewModels<ProductsFragmentViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryIdFromBundle = requireArguments().getInt("category_id")
        val categoryNameFromBundle = requireArguments().getString("category_name")
        initSearch(categoryNameFromBundle!!)
        viewModel.getProductsList(categoryIdFromBundle)
        viewModel.productsList.observe(viewLifecycleOwner) {
            initProductsRv(it)
        }
    }

    private fun initSearch(categoryName: String) {
        binding.productsSearchToolbar.title = "Товары категории '$categoryName'"
        val searchView = binding.productsSearchToolbar.menu.findItem(R.id.products_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                TODO("Not yet implemented")
            }

        })
    }

    private fun productsSearchEngine() {}

    private fun initProductsRv(productsList: ArrayList<ProductModel>) {
        val rvAdapter = ProductsAdapter(productsList, productsClickListener)
        binding.productsRecyclerView.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private val productsClickListener = object : ProductsAdapter.ProductClickListener {
        override fun onDecreaseCounter(binding: ProductsItemBinding) {
            with(binding) {
                val counterValue = addedProductsCounterTV.text.toString().toInt()
                addedProductsCounterTV.text = (counterValue + 1).toString()
            }
        }

        override fun onIncreaseCounter(binding: ProductsItemBinding) {
            with(binding) {
                val counterValue = addedProductsCounterTV.text.toString().toInt()
                addedProductsCounterTV.text = (counterValue - 1).toString()
            }
        }
    }

}