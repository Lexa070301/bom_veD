package com.example.bom_ved

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
// Определение viewModel для передачи данных между Fragment
open class DataModel: ViewModel() {
    val number1: MutableLiveData<Int?> by lazy {
        MediatorLiveData<Int?>()
    }
    val number2: MutableLiveData<Int?> by lazy {
        MediatorLiveData<Int?>()
    }
    val operation: MutableLiveData<String> by lazy {
        MediatorLiveData<String>()
    }
}