package com.example.e_commerceapp.ui.main.add_product

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.observe
import com.example.e_commerceapp.BR
import com.example.e_commerceapp.R
import com.example.e_commerceapp.data.model.Product
import com.example.e_commerceapp.databinding.FragmentAddProductBinding
import com.example.e_commerceapp.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.io.IOException

class AddProductFragment:BaseFragment<FragmentAddProductBinding, AddProductViewModel>() {

    private val imageCode = 101
    private var imageUri: Uri? = null
    private val addProductViewModel: AddProductViewModel by viewModel{ parametersOf(SavedStateHandle(mapOf())) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLayoutAnimations()

        getViewDataBinding().addProductBtn.setOnClickListener {
            when {
                getViewDataBinding().productNameText.text.toString() == "" -> {
                    Toast.makeText(context , "Please enter product name." , Toast.LENGTH_SHORT).show()
                }
                getViewDataBinding().productPriceText.text.toString() == "" -> {
                    Toast.makeText(context , "Please enter product price." , Toast.LENGTH_SHORT).show()
                }
                imageUri == null -> {
                    Toast.makeText(context , "please select product image" , Toast.LENGTH_SHORT).show()
                }
                else -> {
                    // upload image to firebase storage
                    getViewDataBinding().progressBar.visibility = View.VISIBLE

                    imageUri?.let { it1 -> getViewModel().uploadProductImageToFirebase(it1) }
                    getViewModel().productImageLiveData.observe(viewLifecycleOwner) {
                        // push product to specific category
                        val product = Product(
                            getViewDataBinding().productNameText.text.toString() ,
                            Integer.parseInt(getViewDataBinding().productQuantityText.text.toString()) ,
                            getViewDataBinding().productPriceText.text.toString().toDouble() ,
                            getViewModel().productImageLiveData.value.toString() ,
                            getViewDataBinding().productDescriptionText.text.toString()
                        )
                        getViewModel().addProductToFirebase(product,getViewDataBinding().categorySpinner.selectedItem.toString())
                        getViewDataBinding().progressBar.visibility = View.GONE
                        Toast.makeText(context , "product is added." , Toast.LENGTH_SHORT).show()
                        resetViewsInputs()
                    }

                }
            }
        }

        getViewDataBinding().productImage.setOnClickListener{
            openImageChooser()
        }

    }

    private fun setLayoutAnimations(){
        val rightAnimationController: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_right)
        getViewDataBinding().dataCardview.layoutAnimation = rightAnimationController

        val bottomAnimationController: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_bottom)
        getViewDataBinding().btnLayout.layoutAnimation = bottomAnimationController
    }

    private fun openImageChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Product Image"), imageCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == imageCode && resultCode == RESULT_OK && data != null && data.data != null) {
            imageUri = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, imageUri)
                getViewDataBinding().productImage.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun resetViewsInputs(){
        getViewDataBinding().productImage.setImageResource(R.mipmap.item)
        getViewDataBinding().productNameText.setText("")
        getViewDataBinding().productQuantityText.setText("")
        getViewDataBinding().productPriceText.setText("")
        getViewDataBinding().productDescriptionText.setText("")
        getViewDataBinding().productNameText.isFocusable = true
        getViewDataBinding().productNameText.requestFocus()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_shop_cart)
            getNavController().navigate(AddProductFragmentDirections.actionAddProductItemToShopCartItem())
        return super.onOptionsItemSelected(item)
    }

    override val layoutId: Int
        get() = R.layout.fragment_add_product
    override val bindingVariable: Int
        get() = BR.addProductViewModel

    override fun getViewModel(): AddProductViewModel {
        return addProductViewModel
    }

}