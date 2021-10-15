package com.example.bom_ved

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.bom_ved.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ActivityCallBack {
    private lateinit var bindingMain: ActivityMainBinding
    // Добавленные переменные
    private val dataModel: DataModel by viewModels()
    private val buttonList: MutableList<android.widget.Button> = mutableListOf()
    private val fragmentList: MutableList<Fragment> = mutableListOf()
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        // Иницилизация списка фрагментов и кнопок меню
        fragmentList.add(FirstFragment())
        fragmentList.add(SecondFragment())
        fragmentList.add(ThirdFragment())
        fragmentList.add(FourthFragment())

        buttonList.add(bindingMain.include1.button1)
        buttonList.add(bindingMain.include1.button2)
        buttonList.add(bindingMain.include1.button3)
        buttonList.add(bindingMain.include1.button4)

        // Добавление фрагментов в фрагмент менеджер и отключение ненужных фрагментов (detach)
        val transactionInitialization = supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragmentList[0])
            .add(R.id.fragment_container, fragmentList[1])
            .add(R.id.fragment_container, fragmentList[2])
            .add(R.id.fragment_container, fragmentList[3])
            .detach(fragmentList[1])
            .detach(fragmentList[2])
            .detach(fragmentList[3])
            .addToBackStack("initialization fragment")
        transactionInitialization.commit()
    }

    override fun onStart() {
        super.onStart()
        // Добавление тригерра на клик всем кнопкам в меню
        for (i in 0..3){
            buttonList[i].setOnClickListener{
                val transaction = supportFragmentManager
                    .beginTransaction()
                    .detach(fragmentList[position])
                    .attach(fragmentList[i])
                transaction.commit()
                position = i
                buttonSelected()
            }
        }
    }

    // Функция для подсветки рабочей кнопки через position
    private fun buttonSelected(){
        for (i in 0 until position){
            buttonList[i].alpha = 0.5F
        }
        buttonList[position].alpha = 1.0F
        for (i in position+1..3){
            buttonList[i].alpha = 0.5F
        }
    }

    // Функция для перехода к следующему фрагменту
    override fun next() {
        val transaction = supportFragmentManager
            .beginTransaction()
            .detach(fragmentList[position])
            .attach(fragmentList[position+1])
            .addToBackStack("next_fragment")
        transaction.commit()
        position += 1
        buttonSelected()
        buttonList[position].visibility = View.VISIBLE
    }

    // Функция для перехода к предыдущему фрагменту
    override fun prev() {
        val transaction = supportFragmentManager
            .beginTransaction()
            .detach(fragmentList[position])
            .attach(fragmentList[position-1])
            .addToBackStack("prev_fragment")
        transaction.commit()
        position -= 1
        buttonSelected()
    }
}

interface ActivityCallBack {
    fun next()
    fun prev()
}