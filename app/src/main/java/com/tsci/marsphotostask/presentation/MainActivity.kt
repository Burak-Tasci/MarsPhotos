package com.tsci.marsphotostask.presentation

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.tsci.marsphotostask.common.Constants
import com.tsci.marsphotostask.presentation.main.ViewPagerAdapter
import com.tsci.marsphotostask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        val viewPager: ViewPager2 = binding.viewPager
        val tabLayout: TabLayout = binding.tabs

        binding.viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager){
                tab,position ->
            when(position){
                0 -> {
                    tab.text = Constants.Rovers.CURIOUSITY.name
                }
                1 -> {
                    tab.text = Constants.Rovers.OPPORTUNITY.name
                }
                2 -> {
                    tab.text = Constants.Rovers.SPIRIT.name
                }
            }
        }.attach()
    }
}