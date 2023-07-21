package com.example.project_growgh.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.project_growgh.R

class MapsScreen : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_maps_screen, container, false)

        v.apply {
          //  findNavController().navigate(R.id.action_mapsScreen_to_googleMaps)
        }

        return v
    }
}