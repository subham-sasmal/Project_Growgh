package com.example.project_growgh

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController

class HomeScreen : Fragment() {
    private lateinit var feeds: TextView
    private lateinit var uploadImage: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home_screen, container, false)

        v.apply {
            feeds = findViewById(R.id.btn_feeds)
            uploadImage = findViewById(R.id.btn_upload_image)

            feeds.setOnClickListener {
                findNavController().navigate(R.id.action_homeScreen_to_feedsScreenFragment)
            }

            uploadImage.setOnClickListener {
                findNavController().navigate(R.id.action_homeScreen_to_uploadImageFragment2)
            }
        }

        return v
    }
}