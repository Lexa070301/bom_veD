package com.example.bom_ved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bom_ved.databinding.FragmentThirdBinding


class ThirdFragment: Fragment() {
    private lateinit var binding: FragmentThirdBinding
    // Добавленные переменные
    private val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Добавление тригерра на изменения editText, передача данных через ViewModels
        binding.textInput.doAfterTextChanged {
            dataModel.operation.value = binding.textInput.text.toString()
        }
        // Добавление тригерра для перехода на след. fragment
        binding.buttonNext.setOnClickListener{
            val activityCallBack = requireActivity() as ActivityCallBack
            activityCallBack.next()
        }
        // Добавление тригерра для перехода на пред. fragment
        binding.buttonPrev.setOnClickListener{
            val activityCallBack = requireActivity() as ActivityCallBack
            activityCallBack.prev()
        }
    }
}