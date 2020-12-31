package com.example.e_commerceapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anychart.AnyChartView
import com.bumptech.glide.Glide
import com.example.e_commerceapp.data.model.Order
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

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("android:chartData")
    fun setUpChart(anyChartView: AnyChartView, orders:MutableList<Order>){
/*        var carbohydrates = 1
        var fats = 1
        var proteins = 1

        if (recipe.carbos?.isNotEmpty()!!)
            carbohydrates = recipe.carbos.trim()[0].toInt()
        if (recipe.fats?.isNotEmpty()!!)
            fats = recipe.fats.trim()[0].toInt()
        if (recipe.proteins?.isNotEmpty()!!)
            proteins = recipe.proteins.trim()[0].toInt()

        val dataEntries : MutableList<DataEntry> = mutableListOf()
        dataEntries.add(ValueDataEntry("Carbohydrates" , carbohydrates))
        dataEntries.add(ValueDataEntry("Fats" , fats))
        dataEntries.add(ValueDataEntry("Proteins" , proteins))

        val pieChart = AnyChart.pie()
        pieChart.data(dataEntries)
        anyChartView.setChart(pieChart)*/
    }


}