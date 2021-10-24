package com.example.bom_ved

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bom_ved.databinding.ActivityMainBinding
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import java.util.*


class MainActivity : AppCompatActivity(), ActivityCallBack {
    private lateinit var bindingMain: ActivityMainBinding
    // Добавленные переменные
    private var viewHolder: FragmentViewHolder = FragmentViewHolder()
    private var itemDetail: FragmentItemDetail = FragmentItemDetail()
    private val fragmentList: MutableList<Fragment> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        // Иницилизация appBar и кнопки назад в appBar
        setSupportActionBar(bindingMain.appBar)
        bindingMain.appBar.setNavigationOnClickListener {
            val transactionInitialization = supportFragmentManager
                .beginTransaction()
                .detach(fragmentList[1])
                .attach(fragmentList[0])
                .addToBackStack("swap fragment")
            transactionInitialization.commit()
            searchViewChanged(true, "Главная страница")
        }

        // Иницилизация списка фрагментов
        fragmentList.add(viewHolder)
        fragmentList.add(itemDetail)
        val transactionInitialization = supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragmentList[0])
            .add(R.id.fragment_container, fragmentList[1])
            .detach(fragmentList[1])
            .addToBackStack("initialization fragment")
        transactionInitialization.commit()

        // Иницилизация списка для Adapter
        viewHolder.adapter.listItem = UserHolder.createCollectionPictures()
        viewHolder.adapter.notifyDataSetChanged()
    }

    // Подключение searchView в appBar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val search = menu?.findItem(R.id.searchView)
        val searchView = search?.actionView as? SearchView

        searchView?.isSubmitButtonEnabled = false
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchFilter(query)
                }
                return true
            }
            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    searchFilter(query)
                }
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    // Переопределение действия на кнопку назад
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1){
            supportFragmentManager.popBackStack()
        }
        searchViewChanged(true, "Главная страница")
    }

    // Переход в в frame_item_detail и изменение appBar
    override fun showDetails(picture: Picture) {
        val transactionInitialization = supportFragmentManager
            .beginTransaction()
            .detach(fragmentList[0])
            .attach(fragmentList[1])
            .addToBackStack("swap fragment")
        transactionInitialization.commit()
        itemDetail.getPicture(picture)
        searchViewChanged(false, picture.Name)
    }

    // Фильтр массива, изменение локального массива в Adapter и обновление ViewHolder
    @SuppressLint("NotifyDataSetChanged")
    private fun searchFilter(text: String) {
        val searchText = text.lowercase(Locale.getDefault())
        val newPicture = mutableListOf<Picture>()

        if (searchText.isNotEmpty()) {
            viewHolder.adapter.listItem.forEach {
                if (it.Name.lowercase(Locale.getDefault()).contains(text)) {
                    Log.d("Filtered", it.Name)
                    newPicture.add(it)
                }
                viewHolder.adapter.listItem = newPicture
            }
        } else {
            viewHolder.adapter.listItem = UserHolder.createCollectionPictures()
        }
        viewHolder.adapter.notifyDataSetChanged()
    }

    // Включение и выключение кнопки поиска в appBar
    private fun searchViewChanged(active: Boolean, title: String){
        supportActionBar?.setDisplayHomeAsUpEnabled(!active)
        supportActionBar?.setDisplayShowHomeEnabled(!active)
        bindingMain.appBar.findViewById<SearchView>(R.id.searchView).isVisible = active
        supportActionBar?.title = title
    }
}

interface ActivityCallBack {
    fun showDetails(picture: Picture)
}
