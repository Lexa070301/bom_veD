package com.example.bom_ved

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bom_ved.databinding.FrameRecycleViewBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*

class FragmentViewHolder: Fragment() {
    private lateinit var binding: FrameRecycleViewBinding
    private var pictureCollection = UserHolder.createCollectionPictures()

    private val verticalLinearLayoutManager: LinearLayoutManager =
        LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FrameRecycleViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        val bundle = arguments
        if (bundle != null) {
            val any = bundle.getParcelable<BaseParcelable>("2323")?.value
            bundle.putParcelable("picture", BaseParcelable(pictureCollection))

            Log.d("llrrr", any.toString())
        }
    }

    private fun setupRecycleView(){
        binding.recycleView.layoutManager = verticalLinearLayoutManager
        val itemDecorator = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        binding.recycleView.adapter = Adapter(pictureCollection, this::showSnackbar)
    }

    private fun showSnackbar(picture: Picture, trigger: String): Unit{
        var text: String = "Произошла ошибка"
        when (trigger){
            "itemInfo" -> text = "Нажата карточка: " + picture.Name
            "like" -> text = "Нажат лайк: " + picture.Name
        }
        Snackbar.make(binding.root, text, 3000).show()
    }
}