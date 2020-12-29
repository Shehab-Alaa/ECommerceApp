package com.example.e_commerceapp.ui.main.product

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateHandle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerceapp.BR
import com.example.e_commerceapp.R
import com.example.e_commerceapp.data.model.Customer
import com.example.e_commerceapp.data.model.Product
import com.example.e_commerceapp.databinding.FragmentProductsBinding
import com.example.e_commerceapp.ui.base.BaseFragment
import com.example.e_commerceapp.ui.main.HomeActivity
import com.example.e_commerceapp.utils.AppConstants
import com.google.zxing.integration.android.IntentIntegrator
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
import java.util.*


class ProductsFragment:BaseFragment<FragmentProductsBinding, ProductsViewModel>(),
    ProductsAdapter.ProductsAdapterListener {

    private lateinit var productsViewModel : ProductsViewModel
    private val productsAdapter = ProductsAdapter(mutableListOf(), this)
    private val voiceCode = 10
    private lateinit var loginCustomer : Customer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = ProductsFragmentArgs.fromBundle(requireArguments())
        productsViewModel = getViewModel{ parametersOf(SavedStateHandle(mapOf(AppConstants.SELECTED_CATEGORY to args.categoryName))) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginCustomer = (activity as HomeActivity).getLoginCustomer()

        setHasOptionsMenu(true)
        initRecipesRecyclerView()

        getViewDataBinding().productsSearchBox.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // filter ViewModel recipes list according to search entered
                getViewModel().filterProductsData(getViewDataBinding().productsSearchBox.text.toString())
                return@OnEditorActionListener true
            }
            false
        })

        getViewDataBinding().searchByVoiceBtn.setOnClickListener {
            openVoiceSearch(it)
        }

        getViewDataBinding().searchByBarCodeBtn.setOnClickListener {
            openBarCodeSearch()
        }

    }

    private fun initRecipesRecyclerView(){
        getViewDataBinding().productsRv.layoutManager = LinearLayoutManager(context)
        getViewDataBinding().productsRv.setHasFixedSize(true)
        // set Animation to all children (items) of this Layout
        getViewDataBinding().productsRv.layoutAnimation = AnimationUtils.loadLayoutAnimation(
            context,
            R.anim.layout_animation_fall_down
        )
        getViewDataBinding().productsRv.adapter = productsAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_shop_cart)
            getNavController().navigate(ProductsFragmentDirections.actionProductsFragmentToShopCartFragment())
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(view: View, item: Product) {
        getNavController().navigate(
            ProductsFragmentDirections.actionProductsFragmentToProductDetailsFragment(
                item
            )
        )
    }

    override fun onAddToCartClick(view: View, product: Product) {
        // push all products below customer username child
        product.quantity = 1
        getViewModel().addShopCartProduct(loginCustomer.username , product)
        Toast.makeText(context , "product is added to cart" , Toast.LENGTH_SHORT).show()
    }

    private fun openVoiceSearch(view: View) {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        startActivityForResult(intent, voiceCode)
    }

    private fun openBarCodeSearch(){
        val intentIntegrator = IntentIntegrator(activity)
        intentIntegrator.setBeepEnabled(false)
        intentIntegrator.setCameraId(0)
        intentIntegrator.setPrompt("SCAN")
        intentIntegrator.setBarcodeImageEnabled(false)
        intentIntegrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            voiceCode -> if (resultCode == RESULT_OK && data != null) {
                val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                getViewModel().filterProductsData(result!![0])
                getViewDataBinding().productsSearchBox.setText(result[0])
            }else -> getViewModel().filterProductsData(data.toString()) // Result from scanning Barcode.
        }
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