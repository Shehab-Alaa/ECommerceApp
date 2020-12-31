package com.example.e_commerceapp.ui.main.shop_cart

import android.Manifest
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.SavedStateHandle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerceapp.BR
import com.example.e_commerceapp.R
import com.example.e_commerceapp.data.model.CurrentLocation
import com.example.e_commerceapp.data.model.Customer
import com.example.e_commerceapp.data.model.Order
import com.example.e_commerceapp.data.model.Product
import com.example.e_commerceapp.databinding.FragmentShopCartBinding
import com.example.e_commerceapp.ui.base.BaseFragment
import com.example.e_commerceapp.ui.main.HomeActivity
import com.example.e_commerceapp.utils.AppConstants
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
import java.util.*

class ShopCartFragment:BaseFragment<FragmentShopCartBinding, ShopCartViewModel>(),
    ShopCartProductsAdapter.CartProductsAdapterListener , LocationListener{

    private lateinit var shopCartViewModel : ShopCartViewModel
    private lateinit var loginCustomer : Customer
    private val cartProductsAdapter = ShopCartProductsAdapter(mutableListOf(), this)

    private lateinit var order : Order

    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginCustomer = (activity as HomeActivity).getLoginCustomer()
        shopCartViewModel = getViewModel { parametersOf(SavedStateHandle(mapOf(AppConstants.LOGIN_CUSTOMER to loginCustomer.username))) }

        super.onViewCreated(view, savedInstanceState)

        initRecipesRecyclerView()

        getViewDataBinding().orderNowBtn.setOnClickListener {
            // set orderDate , deliveryLocation , total price ==> before OrderCheckout navigation .
            val currentDateTime = Calendar.getInstance().time

            val totalPrice = calcTotalPrice(cartProductsAdapter.getItems())

            getCustomerLocation()

            order = Order(
                loginCustomer.username,
                cartProductsAdapter.getItems(),
                null,
                currentDateTime.toString(),
                totalPrice
            )
        }
    }

    private fun getCustomerLocation() {
        locationManager = (activity as HomeActivity).getSystemService(LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission((activity as HomeActivity), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(activity as HomeActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode
            )
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0f, this);
    }

    override fun onLocationChanged(location: Location) {
        // Navigate when get User Current Location
        order.deliveryLocation = CurrentLocation(location.latitude , location.longitude)
        getNavController().navigate(
            ShopCartFragmentDirections.actionShopCartItemToOrderFragment(
                order
            )
        )
        // remove listener for callback
        locationManager.removeUpdates(this)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calcTotalPrice(productsList: MutableList<Product>) : Double{
        var totalPrice = 0.0
        productsList.onEach {
            totalPrice += (it.price * it.quantity)
        }
        return totalPrice
    }

    private fun initRecipesRecyclerView(){
        getViewDataBinding().cartProductsRv.layoutManager = LinearLayoutManager(context)
        getViewDataBinding().cartProductsRv.setHasFixedSize(true)
        // set Animation to all children (items) of this Layout
        getViewDataBinding().cartProductsRv.layoutAnimation = AnimationUtils.loadLayoutAnimation(
            context,
            R.anim.layout_animation_fall_down
        )
        getViewDataBinding().cartProductsRv.adapter = cartProductsAdapter
    }

    override fun incProductQuantity(view: View, product: Product) {
        getViewModel().updateProductQuantity(product.name, product.quantity)
    }

    override fun decProductQuantity(view: View, product: Product) {
        getViewModel().updateProductQuantity(product.name, product.quantity)
    }

    override fun deleteProductFromCart(view: View, product: Product) {
       getViewModel().deleteShopCartProduct(product.name)
       Toast.makeText(context, "product is removed from cart", Toast.LENGTH_SHORT).show()
    }

    override val layoutId: Int
        get() = R.layout.fragment_shop_cart
    override val bindingVariable: Int
        get() = BR.shopCartViewModel

    override fun getViewModel(): ShopCartViewModel {
        return shopCartViewModel
    }

}