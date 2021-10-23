package com.example.bom_ved

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.bom_ved.databinding.ItemRecyclerviewBinding
import kotlin.reflect.KFunction2

class Adapter(
    private val clickItem: KFunction2<Picture, String, Unit>
): RecyclerView.Adapter<Adapter.Holder>() {

    var listItem: List<Picture> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val picture = listItem[position]
        holder.bind(picture, clickItem)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    inner class Holder internal constructor(private val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(picture: Picture, clickItem: KFunction2<Picture, String, Unit>) = binding.run{
            itemName.text = picture.Name
            imageAvatar.load(picture.imageURL){
                transformations(RoundedCornersTransformation(20f))
            }
            itemDate.text = picture.Date
            itemInformation.text = picture.Information
            itemGenderType.text = picture.Gender

            binding.cardId.setOnClickListener{
                clickItem.invoke(picture, "itemInfo")
            }
            binding.buttonLike.setOnClickListener{
                clickItem.invoke(picture, "like")
            }
            binding.itemName.setOnClickListener{
                clickItem.invoke(picture, "details")
            }
        }
    }
}