package com.example.e_commerceapp.ui.main.product

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateHandle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerceapp.BR
import com.example.e_commerceapp.R
import com.example.e_commerceapp.data.model.Product
import com.example.e_commerceapp.databinding.FragmentProductsBinding
import com.example.e_commerceapp.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProductsFragment:BaseFragment<FragmentProductsBinding , ProductsViewModel>(),
    ProductsAdapter.ProductsAdapterListener {

    private val productsViewModel : ProductsViewModel by viewModel{ parametersOf(SavedStateHandle(mapOf())) }
    private val productsAdapter = ProductsAdapter(mutableListOf() , this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        initRecipesRecyclerView()
    }

    private fun initRecipesRecyclerView(){
        getViewDataBinding().productsRv.layoutManager = LinearLayoutManager(context)
        getViewDataBinding().productsRv.setHasFixedSize(true)
        // set Animation to all children (items) of this Layout
        getViewDataBinding().productsRv.layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
        getViewDataBinding().productsRv.adapter = productsAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_shop_cart)
            getNavController().navigate(ProductsFragmentDirections.actionProductsFragmentToShopCartFragment())
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(view: View, item: Product) {
        getNavController().navigate(ProductsFragmentDirections.actionProductsFragmentToProductDetailsFragment(item))
    }

    override fun onAddToCartClick(view: View, product: Product) {
        TODO("Not yet implemented")
    }

    override val layoutId: Int
        get() = R.layout.fragment_products
    override val bindingVariable: Int
        get() = BR.productsViewModel

    override fun getViewModel(): ProductsViewModel {
        return productsViewModel
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

}