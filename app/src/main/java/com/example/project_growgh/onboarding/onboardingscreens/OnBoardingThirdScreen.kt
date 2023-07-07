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

class OnBoardingThirdScreen : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_on_boarding_third_screen, container, false)

        v.apply {
            findViewById<TextView>(R.id.tv_third_screen_skip_intro).setOnClickListener {
                findNavController().navigate(R.id.action_viewPagerFragment_to_homeScreen)
                onBoardingFinish()
            }

            findViewById<View>(R.id.third_screen_progress_button_bg).setOnClickListener {
                findNavController().navigate(R.id.action_viewPagerFragment_to_homeScreen)
                onBoardingFinish()
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