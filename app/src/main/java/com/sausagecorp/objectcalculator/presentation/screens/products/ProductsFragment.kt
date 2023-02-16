package com.sausagecorp.objectcalculator.presentation.screens.products

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sausagecorp.domain.models.ProductModel
import com.sausagecorp.objectcalculator.R
import com.sausagecorp.objectcalculator.databinding.FragmentProductsBinding
import com.sausagecorp.objectcalculator.databinding.ProductsItemBinding
import com.sausagecorp.objectcalculator.presentation.adapters.ProductsAdapter

class ProductsFragment : Fragment() {

    private lateinit var binding: FragmentProductsBinding
    private val viewModel by viewModels<ProductsFragmentViewModel> { ProductsFragmentViewModel.Factory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryIdFromBundle = requireArguments().getInt("category_id")
        val categoryNameFromBundle = requireArguments().getString("category_name")
        val activity: AppCompatActivity = requireActivity() as AppCompatActivity
        activity.supportActionBar!!.apply {
            show()
            title = "Товары"
        }

        viewModel.getProductsListByCategoryId(categoryIdFromBundle)
        viewModel.productsList.observe(viewLifecycleOwner) {
            initProductsRv(it)
            viewModel.saveProductsToSharedPrefs(it)
        }
        // initMenu()
        binding.productsReadyButton.setOnClickListener {
            viewModel.getProductsListFromDb()
            viewModel.wipeCart()
            viewModel.productsList.observe(viewLifecycleOwner) {
                viewModel.saveProductsList(it)
            }
            findNavController().navigate(R.id.action_productsFragment_to_mainFragment)
        }
    }

    private fun initMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.products_search_menu, menu)
                val searchView: SearchView = menu.findItem(R.id.products_search).actionView as SearchView
                initSearchView(searchView = searchView)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean = when(menuItem.itemId) {
                R.id.products_search -> {
                    println("Search view clicker")
                    true
                }
                else -> false
            }
        })
    }

    private fun initSearchView(searchView: SearchView) {
        searchView.queryHint = "Поиск товара"
        searchView.setOnSearchClickListener {

        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                saveProductsToLiveData()
                productsSearchEngine(query, true)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                saveProductsToLiveData()
                productsSearchEngine(newText)
                return true

            }
        })
        searchView.setOnCloseListener {
            viewModel.getProductsListFromDb()
            viewModel.productsList.observe(viewLifecycleOwner) {
                saveProductsToLiveData()
            }
            searchView.onActionViewCollapsed()
            true
        }
    }


    private fun productsSearchEngine(query: String?, isSubmitted: Boolean = false) {
        if (query == null && isSubmitted) {
            Toast.makeText(context, "Задан пустой поисковой запрос", Toast.LENGTH_SHORT).show()
        }

        val productsList = viewModel.productsList
        val foundProductsList: ArrayList<ProductModel> = arrayListOf()
        for (product in productsList.value!!) {
            if (product.name.contains(query!!, ignoreCase = true)) {
                foundProductsList.add(product)
            }
        }

        if (foundProductsList.size == 0 && isSubmitted) {
            Toast.makeText(context, "По вашему запросу не найдено товаров", Toast.LENGTH_SHORT).show()
        } else {
            initProductsRv(foundProductsList)
        }
    }

    private fun initProductsRv(productsList: ArrayList<ProductModel>) {
        val rvAdapter = ProductsAdapter(productsList, productsClickListener)
        viewModel.saveProductsList(productsList)
        binding.productsRecyclerView.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private val productsClickListener = object : ProductsAdapter.ProductClickListener {
        override fun onDecreaseCounter(binding: ProductsItemBinding, model: ProductModel) {
            with(binding) {
                val counterValue = addedProductsCounterTV.text.toString().toInt()
                addedProductsCounterTV.text = (counterValue + 1).toString()
                productAddedCountTextView.text = (counterValue + 1).toString()
            }
            model.added += 1
            viewModel.saveProduct(model)
        }

        override fun onIncreaseCounter(binding: ProductsItemBinding, model: ProductModel) {
            with(binding) {
                val counterValue = addedProductsCounterTV.text.toString().toInt()
                if (counterValue > 0) {
                    addedProductsCounterTV.text = (counterValue - 1).toString()
                    productAddedCountTextView.text = (counterValue - 1).toString()
                    model.added -= 1
                    viewModel.saveProduct(model)
                }
            }
        }
    }

    private fun saveProductsToLiveData() {
        viewModel.getProductsListFromDb()
        viewModel.productsList.observe(viewLifecycleOwner) {
            if (it.size == 0) {
                initProductsRv(viewModel.getProductsFromSharedPrefs())
            } else {
                initProductsRv(it)
            }
        }
    }

}