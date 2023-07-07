package com.example.project_growgh.onboarding.onboardingscreens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.project_growgh.R

class OnBoardingFirstScreen : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_on_boarding_first_screen, container, false)

        v.apply {
            val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

            findViewById<TextView>(R.id.tv_first_screen_skip_intro).setOnClickListener {
                findNavController().navigate(R.id.action_viewPagerFragment_to_homeScreen)
                onBoardingFinish()
            }

            findViewById<ImageView>(R.id.first_screen_progress_button).setOnClickListener {
                viewPager?.currentItem = 1
            }
        }

        return v
    }

    private fun onBoardingFinish() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }
}