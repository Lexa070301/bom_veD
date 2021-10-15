package com.example.bom_ved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bom_ved.databinding.FragmentSecondsBinding


class SecondFragment: Fragment() {
    private lateinit var binding: FragmentSecondsBinding
    // Добавленные переменные
    private val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Добавление тригерра на изменения editText, передача данных через ViewModels
        binding.textInput.doAfterTextChanged {
            if (binding.textInput.text.toString() != ""){
                dataModel.number2.value = binding.textInput.text.toString().toInt()
            } else dataModel.number2.value = null
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