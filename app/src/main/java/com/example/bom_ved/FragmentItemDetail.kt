package com.example.bom_ved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bom_ved.databinding.FrameItemDetailBinding

class FragmentItemDetail: Fragment() {
    private lateinit var binding: FrameItemDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FrameItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
}