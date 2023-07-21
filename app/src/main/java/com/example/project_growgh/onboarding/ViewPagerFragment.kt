package com.example.project_growgh.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.project_growgh.R
import com.example.project_growgh.onboarding.onboardingscreens.OnBoardingFirstScreen
import com.example.project_growgh.onboarding.onboardingscreens.OnBoardingSecondScreen
import com.example.project_growgh.onboarding.onboardingscreens.OnBoardingThirdScreen

class ViewPagerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_view_pager, container, false)

        v.apply {
            val fragmentList = arrayListOf<Fragment>(
                OnBoardingFirstScreen(),
                OnBoardingSecondScreen(),
                OnBoardingThirdScreen()
            )

            val adapter = OnBoardingViewPagerAdapter(
                fragmentList,
                requireActivity().supportFragmentManager,
                lifecycle
            )

            findViewById<ViewPager2>(R.id.viewPager).adapter = adapter
        }

        return v
    }
}