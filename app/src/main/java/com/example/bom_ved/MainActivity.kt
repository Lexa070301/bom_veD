package com.example.bom_ved

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bom_ved.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import android.R

import android.app.SearchManager
import android.widget.SearchView


class MainActivity : AppCompatActivity() {
    private lateinit var bindingMain: ActivityMainBinding


    private val verticalLinearLayoutManager: LinearLayoutManager =
        LinearLayoutManager(this, RecyclerView.VERTICAL, false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)
        setSupportActionBar(bindingMain.appBar)
        title = "My new title"
        setupRecycleView()
    }

    private fun setupRecycleView(){
        bindingMain.recycleView.layoutManager = verticalLinearLayoutManager
        val itemDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        bindingMain.recycleView.adapter = Adapter(UserHolder.createCollectionPictures(), this::showSnackbar)
    }

    private fun showSnackbar(picture: Picture, trigger: String): Unit{
        var text: String = "Произошла ошибка"
        when (trigger){
            "itemInfo" -> text = "Нажата карточка: " + picture.Name
            "like" -> text = "Нажат лайк: " + picture.Name
        }
        Snackbar.make(bindingMain.root, text, 3000).show()
    }
}