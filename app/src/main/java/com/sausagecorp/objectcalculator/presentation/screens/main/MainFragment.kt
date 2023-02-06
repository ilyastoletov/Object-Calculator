package com.sausagecorp.objectcalculator.presentation.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sausagecorp.domain.models.ProductModel
import com.sausagecorp.objectcalculator.R
import com.sausagecorp.objectcalculator.databinding.FragmentMainBinding
import com.sausagecorp.objectcalculator.presentation.adapters.ProductsMainScreenAdapter
import kotlin.math.roundToInt

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModels<MainFragmentViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity: AppCompatActivity = requireActivity() as AppCompatActivity
        activity.supportActionBar!!.hide()

        initCountButton()
        initProductsRv()
        viewModel.objectVolume.observe(viewLifecycleOwner) {
            binding.volumeTextView.text = it.toString().format(".3f", it)
        }

        binding.chooseProductsButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_categoriesFragment)
        }

    }

    private fun initCountButton() = binding.countVolumeButton.setOnClickListener {
        val a = binding.objectHeightEditText.text
        val b = binding.objectWidthEditText.text
        val c = binding.objectTopHeightEditText.text

        if (a.isNotEmpty() && b.isNotEmpty() && c.isNotEmpty()) {
            viewModel.countObjectVolume(
                a.toString().toDouble(), b.toString().toDouble(), c.toString().toDouble()
            )
        } else {
            Toast.makeText(context, "Введите значения", Toast.LENGTH_SHORT).show()
        }

    }

    private fun initProductsRv() {
        val productsList: ArrayList<ProductModel> = arrayListOf()
        val adapterProducts = ProductsMainScreenAdapter(productsList)
        val rv = binding.productsRv
        rv.apply {
            adapter = adapterProducts
            layoutManager = LinearLayoutManager(context)
        }
    }

}