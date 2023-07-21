package com.example.project_growgh.ui

import android.app.Activity.RESULT_OK
import android.content.Intent
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
import com.example.project_growgh.R
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog

class UploadImageFragment : Fragment(), EasyPermissions.PermissionCallbacks {
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
                requestForGalleryAccess()
                openGallery()
            }

            clearImage.setOnClickListener {
                imageSelected.setImageDrawable(null)
                clearImage.setImageDrawable(null)

                Toast.makeText(context, "Image Cleared", Toast.LENGTH_SHORT).show()
            }
        }

        return v
    }

    private fun hasGalleryPermission() = EasyPermissions.hasPermissions(
        requireContext(),
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )

    private fun requestForGalleryAccess() {
        EasyPermissions.requestPermissions(
            this,
            "Please give storage access",
            1,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
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


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if (EasyPermissions.somePermissionDenied(this, perms.first())) {
            SettingsDialog.Builder(requireActivity()).build().show()
        } else {
            requestForGalleryAccess()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        openGallery()
    }

    private fun openGallery() {
//        requestForGalleryAccess()

        if (hasGalleryPermission()) {
            Intent(Intent.ACTION_GET_CONTENT).also {
                it.type = "image/*"
                resultLauncher.launch(it)
            }
        }
    }
}