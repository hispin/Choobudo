package com.generosity.choobudo.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.generosity.choobudo.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainBoardFragment : Fragment() {

    private var on_boarding_view_pager: ViewPager2?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_main_board, container, false)
        on_boarding_view_pager = view.findViewById(R.id.on_boarding_view_pager)

        val numberOfScreens =
            resources.getStringArray(R.array.banners_array).size
        val onBoardingAdapter = OnBoardingAdapter(requireActivity() as AppCompatActivity, numberOfScreens)
        on_boarding_view_pager?.offscreenPageLimit = 1
        on_boarding_view_pager?.adapter = onBoardingAdapter
        //on_boarding_view_pager?.registerOnPageChangeCallback(onBoardingPageChangeCallback)

        //for indicators dots
        if (on_boarding_view_pager != null) {
            val tabLayout =view.findViewById<TabLayout>(R.id.tab_layout)
            val tabLayoutMediator = TabLayoutMediator(
                tabLayout, on_boarding_view_pager!!, true
            ) { tab, position -> }
            tabLayoutMediator.attach()
        }
        return view
    }


}