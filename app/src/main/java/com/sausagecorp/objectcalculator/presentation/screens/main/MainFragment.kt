package com.sausagecorp.objectcalculator.presentation.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.sausagecorp.objectcalculator.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModels<MainFragmentViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCountButton()
        viewModel.objectVolume.observe(viewLifecycleOwner) {
            binding.volumeTextView.text = it.toString()
        }

    }

    private fun initCountButton() = binding.countVolumeButton.setOnClickListener {
        viewModel.countObjectVolume(
            binding.objectHeightEditText.text.toString().toDouble(),
            binding.objectWidthEditText.text.toString().toDouble(),
            binding.objectTopHeightEditText.text.toString().toDouble()
        )
    }

}