package com.example.lesson_6_lukin.thirdScreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.lesson_6_lukin.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator

class SetedFragment : Fragment(R.layout.fragment_seted) {

    companion object {
        fun newInstance(): SetedFragment {
            return SetedFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        val tableLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val springDotsIndicator = view.findViewById<SpringDotsIndicator>(R.id.dotsIndicator)
        viewPager.adapter = PagerAdapter(this)
        springDotsIndicator.setViewPager2(viewPager)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewPager.isUserInputEnabled
            }
        })
        TabLayoutMediator(tableLayout, viewPager) { tab, position -> }.attach()
    }

}