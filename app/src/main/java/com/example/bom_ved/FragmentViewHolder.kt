package com.example.bom_ved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bom_ved.databinding.FrameRecycleViewBinding
import com.google.android.material.snackbar.Snackbar


class FragmentViewHolder: Fragment() {
    private lateinit var binding: FrameRecycleViewBinding
    // Добавленные переменные
    var adapter: Adapter = Adapter( this::showSnackbar)

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
        // иницилизация recycleView
        setupRecycleView()
    }

    // Подключение и настройка recycleView
    private fun setupRecycleView(){
        binding.recycleView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.recycleView.adapter = adapter
    }

    // Обработка нажатий на карточки в ViewHolder
    private fun showSnackbar(picture: Picture, trigger: String): Unit{
        var text: String = "Произошла ошибка"
        when (trigger){
            "itemInfo" -> text = "Нажата карточка: " + picture.Name
            "like" -> text = "Нажат лайк: " + picture.Name
            "details" -> {
                val activityCallBack = requireActivity() as ActivityCallBack
                activityCallBack.showDetails(picture)
                return
            }
        }
        Snackbar.make(binding.root, text, 3000).show()
    }
}