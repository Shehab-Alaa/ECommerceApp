package com.example.e_commerceapp.utils

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.anychart.core.cartesian.series.Column
import com.anychart.enums.Anchor
import com.anychart.enums.HoverMode
import com.anychart.enums.Position
import com.anychart.enums.TooltipPositionMode
import com.bumptech.glide.Glide
import com.example.e_commerceapp.data.model.Order
import com.example.e_commerceapp.ui.base.BaseRecyclerViewAdapter


object BindingAdaptersUtils {

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("android:recyclerViewAdapter")
    fun <T> setRecyclerViewData(recyclerView: RecyclerView, items: MutableList<T>?) {
        items?.let { (recyclerView.adapter as BaseRecyclerViewAdapter<T>?)?.addItems(it) }
    }

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("android:imageSrc")
    fun setImageViewSrc(imageVew: ImageView, imagePath: String?){
        Glide.with(imageVew.context)
            .load(imagePath)
            .into(imageVew);
    }

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("android:chartData")
    fun setUpChart(anyChartView: AnyChartView, orders: MutableList<Order>?){

        if (orders != null) { // to draw only when data is available
            val cartesian: Cartesian = AnyChart.column()
            cartesian.xScroller().enabled(true)

            val data: MutableList<DataEntry> = mutableListOf()
            for (order in orders) {
                for (product in order.products) {
                    data.add(ValueDataEntry(product.name, product.quantity))
                }
            }

            val column: Column = cartesian.column(data)

            column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0)
                .offsetY(1)
                .format("{%Value}{groupsSeparator: }")

            cartesian.animation(true)
            cartesian.title("Orders History")

            cartesian.yScale().minimum(1)
            cartesian.yScale().maximum(20)

            cartesian.yAxis(0).labels().format("{%Value}{decimalsCount:1}")

            cartesian.tooltip().positionMode(TooltipPositionMode.POINT)
            cartesian.interactivity().hoverMode(HoverMode.BY_X)

            cartesian.xAxis(0).title("Product")
            cartesian.yAxis(0).title("Quantity")

            anyChartView.setChart(cartesian)
        }

    }


}