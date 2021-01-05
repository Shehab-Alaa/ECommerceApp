package com.example.e_commerceapp.ui.main.product_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.transition.TransitionInflater
import com.example.e_commerceapp.BR
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentProductDetailsBinding
import com.example.e_commerceapp.ui.base.BaseFragment
import com.example.e_commerceapp.ui.main.product.ProductsFragmentArgs
import com.example.e_commerceapp.ui.main.product.ProductsFragmentDirections
import com.example.e_commerceapp.utils.AppConstants
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProductDetailsFragment:BaseFragment<FragmentProductDetailsBinding , ProductDetailsViewModel>() {

    private lateinit var productDetailsViewModel : ProductDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = ProductDetailsFragmentArgs.fromBundle(requireArguments())
        productDetailsViewModel = getViewModel{ parametersOf(SavedStateHandle(mapOf(AppConstants.SELECTED_PRODUCT to args.selectedProduct))) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        initToolbar()
        return getRootView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLayoutAnimations()
    }

    private fun setLayoutAnimations(){
        getViewDataBinding().productImage.transitionName = getViewModel().productLiveData.value?.image
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        val rightAnimationController: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_right)
        getViewDataBinding().detailsCardView.layoutAnimation = rightAnimationController
    }

    private fun initToolbar() {
        val layout = getViewDataBinding().collapsingToolbar
        val toolbar = getViewDataBinding().toolbar
        val navController = activity?.let { Navigation.findNavController(it,R.id.nav_host_fragment) }
        val appBarConfiguration = navController?.graph?.let { AppBarConfiguration.Builder(it).build() }
        if (navController != null) {
            if (appBarConfiguration != null) {
                NavigationUI.setupWithNavController(layout, toolbar, navController, appBarConfiguration)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_shop_cart)
            getNavController().navigate(ProductDetailsFragmentDirections.actionProductDetailsFragmentToShopCartFragment())
        return super.onOptionsItemSelected(item)
    }

    override val layoutId: Int
        get() = R.layout.fragment_product_details
    override val bindingVariable: Int
        get() = BR.productDetailsViewModel

    override fun getViewModel(): ProductDetailsViewModel {
        return productDetailsViewModel
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }
}