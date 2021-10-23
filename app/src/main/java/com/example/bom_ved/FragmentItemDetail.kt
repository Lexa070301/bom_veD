package com.example.bom_ved

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.bom_ved.databinding.FrameItemDetailBinding

class FragmentItemDetail: Fragment() {
    private lateinit var binding: FrameItemDetailBinding
    private lateinit var pictureItem: Picture


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FrameItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (this::pictureItem.isInitialized){
            binding.itemName.text = pictureItem.Name
            binding.imageAvatar.load(pictureItem.imageURL){
                transformations(RoundedCornersTransformation(20f))
            }
            binding.itemDate.text = pictureItem.Date
            binding.itemInformation.text = pictureItem.Information
            binding.itemGenderType.text = pictureItem.Gender
        }
    }


    fun getPicture(picture: Picture){
        pictureItem = picture
    }
}