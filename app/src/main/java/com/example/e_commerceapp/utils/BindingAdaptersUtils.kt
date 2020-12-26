package com.example.e_commerceapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerceapp.ui.base.BaseRecyclerViewAdapter



object BindingAdaptersUtils {

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("android:recyclerViewAdapter")
    fun <T> setRecyclerViewData(recyclerView: RecyclerView, items:MutableList <T>?) {
        items?.let { (recyclerView.adapter as BaseRecyclerViewAdapter<T>?)?.addItems(it) }
    }

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("android:imageSrc")
    fun setImageViewSrc(imageVew: ImageView , imagePath : String?){
        Glide.with(imageVew.context)
            .load(imagePath)
            .into(imageVew);
    }


}