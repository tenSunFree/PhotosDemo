package com.example.photosdemo.dynamic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.photosdemo.common.base.BaseFragment
import com.example.photosdemo.databinding.FragmentDynamicBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DynamicFragment : BaseFragment() {

    private var _binding: FragmentDynamicBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDynamicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}