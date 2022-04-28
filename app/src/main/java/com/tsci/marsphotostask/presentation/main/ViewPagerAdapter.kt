package com.tsci.marsphotostask.presentation.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tsci.marsphotostask.R
import com.tsci.marsphotostask.common.Constants
import com.tsci.marsphotostask.common.Constants.Rovers.*

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(
        fragmentManager, lifecycle
    ) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                MainFragment.newInstance(rover = CURIOUSITY)
            }
            1 -> {
                MainFragment.newInstance(rover = OPPORTUNITY)
            }
            2 -> {
                MainFragment.newInstance(rover = SPIRIT)
            }
            else -> {
                Fragment()
            }
        }
    }


}