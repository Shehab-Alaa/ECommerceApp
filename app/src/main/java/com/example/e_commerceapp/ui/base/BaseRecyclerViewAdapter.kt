package com.example.e_commerceapp.ui.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T>(private val items: MutableList<T>) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    private fun clearItems() {
        items.clear()
    }

    open fun addItems(items: List<T>) {
        clearItems()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getItems(): MutableList<T> {
        return items
    }

}
