package com.example.e_commerceapp.ui.base

import android.view.View

interface BaseItemListener<T> {
    fun onItemClick(view: View, item: T)
}