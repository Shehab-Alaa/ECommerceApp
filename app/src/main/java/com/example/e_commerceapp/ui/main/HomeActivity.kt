package com.example.e_commerceapp.ui.main

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.e_commerceapp.BR
import com.example.e_commerceapp.R
import com.example.e_commerceapp.data.model.Customer
import com.example.e_commerceapp.databinding.ActivityHomeBinding
import com.example.e_commerceapp.ui.base.BaseActivity
import com.example.e_commerceapp.utils.AppConstants
import com.google.android.material.navigation.NavigationView
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeActivity : BaseActivity<ActivityHomeBinding , HomeViewModel>() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private val homeViewModel: HomeViewModel by viewModel{ parametersOf(SavedStateHandle(mapOf())) }
    private lateinit var customer : Customer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        customer = intent.getSerializableExtra(AppConstants.LOGIN_CUSTOMER) as Customer

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = getViewDataBinding().drawerLayout
        val navView: NavigationView = getViewDataBinding().navView
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.watchesItem , R.id.mobilesItem , R.id.tvsItem , R.id.addProductItem , R.id.customerProfileItem
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun getViewModel(): HomeViewModel {
        return homeViewModel
    }

    override val bindingVariable: Int
        get() = BR.homeViewModel


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun getLoginCustomer() = customer
}