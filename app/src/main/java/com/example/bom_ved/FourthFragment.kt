package com.example.bom_ved

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bom_ved.databinding.FragmentFourthBinding

class FourthFragment: Fragment() {
    private lateinit var binding: FragmentFourthBinding
    // Добавленные переменные
    private val dataModel: DataModel by activityViewModels()
    private var num1: Int? = null
    private var num2: Int? = null
    private var oper: String = ""
    private var result: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFourthBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Включение наблюдатебя после создания View
        initialization()
        // Добавление тригерра для перехода на пред. fragment
        binding.buttonPrev.setOnClickListener{
            val activityCallBack = requireActivity() as ActivityCallBack
            activityCallBack.prev()
        }
    }
    // Подключение наблюдателя для данных с DataModel и последующие записывание в переменные
    private fun initialization(){
        dataModel.number1.observe(viewLifecycleOwner){
            num1 = it
        }
        dataModel.number2.observe(viewLifecycleOwner){
            num2 = it
        }
        dataModel.operation.observe(viewLifecycleOwner){
            oper = it
            calculator()
        }
    }

    // Переопределение переменных
    private fun calculator(){
        // Проверка всех переменных
        if (num1 == null || num2 == null) return
        result = when (oper) {
            "+" -> num1!!.plus(num2!!)
            "-" -> num1!!.minus(num2!!)
            "*" -> num1!!.times(num2!!)
            "/" -> num1!!.div(num2!!)
            else -> return
        }
        // Подсчет результата
        binding.textOutputInfo.text = "$num1 $oper $num2 = $result"
        binding.textOutputInt.text = result.toString()
    }
}