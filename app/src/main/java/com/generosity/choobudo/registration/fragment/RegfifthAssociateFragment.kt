package com.generosity.choobudo.registration.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.generosity.choobudo.R
import java.io.ByteArrayOutputStream


class RegfifthAssociateFragment : Fragment() {

    var ivAddGallery: AppCompatImageView?=null
    var photo64: String?=null


    private val launcher: ActivityResultLauncher<Intent> = registerForActivityResult<Intent, androidx.activity.result.ActivityResult>(
        ActivityResultContracts.StartActivityForResult(),
    ActivityResultCallback<ActivityResult> { result: ActivityResult ->
        if ((result.resultCode == Activity.RESULT_OK && result.data != null)) {
            if (result.data != null) {
                val photoUri: Uri?=result.data!!.data
                ivAddGallery?.setImageURI(photoUri)
                if (photoUri != null) {
                    photo64=encodeImage(photoUri)
                }
            }
            //use photoUri here
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_regfifth_associate, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View?) {
        ivAddGallery = view?.findViewById(R.id.ivAddGallery)
        ivAddGallery?.setOnClickListener {
            val intent=Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            launcher.launch(intent)
        }
    }

    /**
     * convert Uri to Base64
     */
    private fun encodeImage(imageUri: Uri): String? {
        val imageStream = requireActivity().contentResolver.openInputStream(imageUri)
        val selectedImage = BitmapFactory.decodeStream(imageStream)
        val baos=ByteArrayOutputStream()
        selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b: ByteArray=baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

}