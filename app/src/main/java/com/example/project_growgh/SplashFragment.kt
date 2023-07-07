package com.example.project_growgh

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController

class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_splash, container, false)

        v.apply {

            Handler().postDelayed(
                {
                    if (onBoardingFinished()) {
                        findNavController().navigate(R.id.action_splashFragment2_to_homeScreen)
                    } else {
                        findNavController().navigate(R.id.action_splashFragment2_to_viewPagerFragment)
                    }
                },
                1000
            )
        }

        return v
    }

    private fun onBoardingFinished(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }
}