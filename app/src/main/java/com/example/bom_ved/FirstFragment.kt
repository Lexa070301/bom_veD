package com.example.bom_ved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.bom_ved.databinding.FragmentFirstBinding
import androidx.fragment.app.activityViewModels


class FirstFragment: Fragment() {
    private lateinit var binding: FragmentFirstBinding
    // Добавленные переменные
    private val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Добавление тригерра на изменения editText, передача данных через ViewModels
        binding.textInput.doAfterTextChanged {
            if (binding.textInput.text.toString() != ""){
                dataModel.number1.value = binding.textInput.text.toString().toInt()
            } else dataModel.number1.value = null
        }
        // Добавление тригерра для перехода на след. fragment
        binding.button.setOnClickListener{
            val activityCallBack = requireActivity() as ActivityCallBack
            activityCallBack.next()
        }
    }
}