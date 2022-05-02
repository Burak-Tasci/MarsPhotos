package com.tsci.marsphotostask.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.tsci.marsphotostask.common.Constants
import com.tsci.marsphotostask.databinding.ActivityMainBinding
import com.tsci.marsphotostask.presentation.adapters.ViewPagerAdapter
import com.tsci.marsphotostask.presentation.ui.FilterWindow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = Constants.Rovers.CURIOSITY.name
                    1 -> tab.text = Constants.Rovers.OPPORTUNITY.name
                    2 -> tab.text = Constants.Rovers.SPIRIT.name
                }
            }.attach()

            filterButton.setOnClickListener {
                FilterWindow().show(
                    supportFragmentManager,
                    "Filter Popup Window"
                )
            }
        }
    }
}