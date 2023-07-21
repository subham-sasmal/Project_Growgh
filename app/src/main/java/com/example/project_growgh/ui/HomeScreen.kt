package com.example.project_growgh.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.project_growgh.R
import com.example.project_growgh.ScreensViewPagerAdapter
import com.example.project_growgh.onboarding.OnBoardingViewPagerAdapter
import com.example.project_growgh.onboarding.onboardingscreens.OnBoardingFirstScreen
import com.example.project_growgh.onboarding.onboardingscreens.OnBoardingSecondScreen
import com.example.project_growgh.onboarding.onboardingscreens.OnBoardingThirdScreen
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeScreen : Fragment() {
    private lateinit var homeScreen: LinearLayout
    private lateinit var videoScreen: LinearLayout
    private lateinit var mapScreen: LinearLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home_screen, container, false)

        v.apply {
            homeScreen = findViewById(R.id.homeScreen)
            videoScreen = findViewById(R.id.videoScreen)
            mapScreen = findViewById(R.id.mapScreen)
            viewPager = findViewById(R.id.main_screen_viewpager)
            tabLayout = findViewById(R.id.tab_layout)


            val fragmentList = arrayListOf<Fragment>(
                FeedsScreenFragment(),
                VideoScreenFragment(),
                CurrentLocationInGoogleMaps()
            )


            val adapter = ScreensViewPagerAdapter(
                fragmentList,
                requireActivity().supportFragmentManager,
                lifecycle
            )

//            val adapter = ScreensViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
            viewPager.adapter = adapter

            TabLayoutMediator(tabLayout, viewPager) {
                tab, pos ->
                    when (pos) {
                        0 -> {}
                        1 -> {}
                        2 -> {
                            //findNavController().navigate(R.id.action_homeScreen_to_googleMaps)
                        }
                    }
            }.attach()
        }

        return v
    }
}