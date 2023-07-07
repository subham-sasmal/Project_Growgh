package com.example.project_growgh

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.findNavController
import java.io.File

class UploadImageFragment : Fragment() {
    private lateinit var btnSelectImage: TextView
    private lateinit var imageSelected: ImageView
    private lateinit var clearImage: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_upload_image, container, false)

        v.apply {
            btnSelectImage = findViewById(R.id.uploadScreen_btn_select_image)
            imageSelected = findViewById(R.id.img_holder)
            clearImage = findViewById(R.id.clear_image)

            findViewById<View>(R.id.btn_nav_back).setOnClickListener {
                findNavController().popBackStack()
            }

            btnSelectImage.setOnClickListener() {
                Intent(Intent.ACTION_GET_CONTENT).also {
                    it.type = "image/*"
                    resultLauncher.launch(it)
                }
            }

            clearImage.setOnClickListener {
                imageSelected.setImageDrawable(null)
                clearImage.setImageDrawable(null)

                Toast.makeText(context, "Image Cleared", Toast.LENGTH_SHORT).show()
            }
        }

        return v
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result?.data?.data?.let {
                    imageSelected.setImageURI(it)
                    clearImage.setImageResource(R.drawable.image_clear_icon)
                }
            }
        }
}