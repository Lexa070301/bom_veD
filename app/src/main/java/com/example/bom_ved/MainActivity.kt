package com.example.bom_ved

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bom_ved.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var bindingMain: ActivityMainBinding


    private val verticalLinearLayoutManager: LinearLayoutManager =
        LinearLayoutManager(this, RecyclerView.VERTICAL, false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingMain = ActivityMainBinding.inflate(layoutInflater)

        setContentView(bindingMain.root)
        setSupportActionBar(bindingMain.appBar)
        toolBarConfiguration(bindingMain.appBar)
        setupRecycleView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val search = menu?.findItem(R.id.searchView)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)


        return super.onCreateOptionsMenu(menu)
    }

    private fun toolBarConfiguration(toolBar: androidx.appcompat.widget.Toolbar){
        toolBar
    }

    private fun setupRecycleView(){
        bindingMain.recycleView.layoutManager = verticalLinearLayoutManager
        val itemDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        val searchView = androidx.appcompat.widget.SearchView.generateViewId()
        bindingMain.recycleView.adapter = Adapter(UserHolder.createCollectionPictures(), this::showSnackbar)
    }

    private fun showSnackbar(picture: Picture, trigger: String): Unit{
        var text: String = "Произошла ошибка"
        when (trigger){
            "itemInfo" -> text = "Нажата карточка: " + picture.Name
            "like" -> text = "Нажат лайк: " + picture.Name
        }
        Log.d("llolol", bindingMain.appBar.menu.toString())
        Snackbar.make(bindingMain.root, text, 3000).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            Log.d("thanks", newText)
        }
        return true
    }
}