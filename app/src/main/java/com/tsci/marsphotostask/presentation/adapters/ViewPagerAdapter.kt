package com.tsci.marsphotostask.presentation.adapters

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tsci.marsphotostask.presentation.ui.CuriosityFragment
import com.tsci.marsphotostask.presentation.ui.OpportunityFragment
import com.tsci.marsphotostask.presentation.ui.SpiritFragment

private const val TAG = "ViewPagerAdapter.kt"
internal class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(
        fragmentManager, lifecycle
    ) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        Log.d(TAG, "createFragment: $position")
        return when(position){

            0 -> CuriosityFragment()
            1 -> OpportunityFragment()
            2 -> SpiritFragment()
            else -> throw Exception("Invalid tab layout position.")
        }
    }


}