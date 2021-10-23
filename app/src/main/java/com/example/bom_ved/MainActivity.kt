package com.example.bom_ved

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bom_ved.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.bom_ved.databinding.FrameRecycleViewBinding
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private lateinit var bindingMain: ActivityMainBinding
    private lateinit var bindindFragmentViewHolder: FrameRecycleViewBinding
    private lateinit var adapter: Adapter
    private val fragmentList: MutableList<Fragment> = mutableListOf()



    private val verticalLinearLayoutManager: LinearLayoutManager =
        LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    private var pictureCollection = UserHolder.createCollectionPictures()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)
        setSupportActionBar(bindingMain.appBar)
        setupRecycleView()

//        fragmentList.add(FragmentViewHolder())
//        fragmentList.add(FragmentItemDetail())
//
//        val transactionInitialization = supportFragmentManager
//            .beginTransaction()
//            .add(R.id.fragment_container, fragmentList[0])
//            .add(R.id.fragment_container, fragmentList[1])
//            .detach(fragmentList[1])
//            .addToBackStack("initialization fragment")
//        transactionInitialization.commit()
//        val bundle = Bundle()
//        bundle.putParcelable("2323", BaseParcelable(fragmentList))
//        fragmentList[0].arguments = bundle
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val search = menu?.findItem(R.id.searchView)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = false
        searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
//                    val any = arguments.getParcelable<BaseParcelable>("picture")?.value
//                    Log.d("PICTURE", any.toString())

                    searchFilter(newText)
                }
                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun searchFilter(text: String){
        val searchText = text.lowercase(Locale.getDefault())
        val newPicture = mutableListOf<Picture>()

        if (searchText.isNotEmpty()){
            pictureCollection.forEach{
                if (it.Name.lowercase(Locale.getDefault()).contains(text)){
                    Log.d("Filtered", it.Name)
                    newPicture.add(it)
                }
                adapter.listItem = newPicture
            }
        } else {
            adapter.listItem = UserHolder.createCollectionPictures()
        }
        bindingMain.recycleView.adapter?.notifyDataSetChanged()
    }

    private fun setupRecycleView(){
        bindingMain.recycleView.layoutManager = verticalLinearLayoutManager
        val itemDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        adapter = Adapter(this::showSnackbar)
        adapter.listItem = UserHolder.createCollectionPictures()
        bindingMain.recycleView.adapter = adapter
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



//interface ActivityCallBack {
//    fun searchFilter(inputText: String): String
//}
//
//class BaseParcelable : Parcelable {
//
//    var value: Any
//
//    constructor(value: Any) {
//        this.value = value
//    }
//
//    constructor(parcel: Parcel) {
//        this.value = Any()
//    }
//
//    override fun writeToParcel(dest: Parcel?, flags: Int) {}
//
//    override fun describeContents(): Int = 0
//
//    companion object CREATOR : Parcelable.Creator<BaseParcelable> {
//
//        override fun createFromParcel(parcel: Parcel): BaseParcelable {
//            return BaseParcelable(parcel)
//        }
//
//        override fun newArray(size: Int): Array<BaseParcelable?> {
//            return arrayOfNulls(size)
//        }
//    }
//}