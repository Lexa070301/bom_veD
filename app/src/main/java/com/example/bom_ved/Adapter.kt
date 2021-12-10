package com.example.bom_ved


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bom_ved.databinding.ItemRecyclerviewBinding


class Adapter: RecyclerView.Adapter<Adapter.Holder>() {
    // Добавленные переменные
    var listItem: List<String> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val text = listItem[position]
        holder.bind(text)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    inner class Holder internal constructor(private val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(text: String) = binding.run{
            textView.text = text
        }
    }

}