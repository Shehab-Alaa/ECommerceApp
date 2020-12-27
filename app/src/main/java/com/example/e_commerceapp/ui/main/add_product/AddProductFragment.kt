package com.example.e_commerceapp.ui.main.add_product

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import com.example.e_commerceapp.BR
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentAddProductBinding
import com.example.e_commerceapp.ui.base.BaseFragment
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.StorageReference
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.io.IOException

class AddProductFragment:BaseFragment<FragmentAddProductBinding, AddProductViewModel>() {

    private val IMAGE_CODE = 101
    private var imageUri: Uri? = null
    private val addProductViewModel: AddProductViewModel by viewModel{ parametersOf(SavedStateHandle(mapOf())) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewDataBinding().addProductBtn.setOnClickListener {
            if (getViewDataBinding().productNameText.text.toString() == ""){
                Toast.makeText(context , "Please enter product name." , Toast.LENGTH_SHORT)
            }else if (getViewDataBinding().productPriceText.text.toString() == ""){
                Toast.makeText(context , "Please enter product price." , Toast.LENGTH_SHORT)
            }else if (imageUri != null){
                Toast.makeText(context , "please select product image" , Toast.LENGTH_SHORT)
            }else{
                // upload image to firebase storage
                // push product to specific category
            }
        }

        getViewDataBinding().productImage.setOnClickListener{
            openImageChooser()
        }

    }

    private fun openImageChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Product Image"), IMAGE_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_CODE && resultCode == RESULT_OK && data != null && data.data != null) {
            imageUri = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, imageUri)
                getViewDataBinding().productImage.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    /*private fun uploadImageToFirebaseStorage() {
        val profilePictureRef: StorageReference = FirebaseStorage.getInstance()
            .getReference("profilepics/" + System.currentTimeMillis() + ".png")
        if (imageUri != null) {
            progressBar.setVisibility(View.VISIBLE)
            // upload image to storage
            profilePictureRef.putFile(imageUri)
                .addOnSuccessListener(OnSuccessListener<Any> { taskSnapshot ->
                    progressBar.setVisibility(View.GONE)
                    imageUrl = taskSnapshot.getDownloadUrl().toString()
                }).addOnFailureListener(OnFailureListener {
                    progressBar.setVisibility(View.GONE)
                    Toast.makeText(getApplicationContext(), "uploaded failed ", Toast.LENGTH_SHORT)
                        .show()
                })
        }
    }*/

    override val layoutId: Int
        get() = R.layout.fragment_add_product
    override val bindingVariable: Int
        get() = BR.addProductViewModel

    override fun getViewModel(): AddProductViewModel {
        return addProductViewModel
    }

}