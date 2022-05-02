package com.tsci.marsphotostask.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.tsci.marsphotostask.common.Constants.Rovers.*
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
                    0 -> tab.text = CURIOSITY.name
                    1 -> tab.text = OPPORTUNITY.name
                    2 -> tab.text = SPIRIT.name
                }
            }.attach()


            filterButton.setOnClickListener {
                val currentLayout = binding.tabs.getSelectedTabPosition()
                when (currentLayout ){
                    0 -> FilterWindow(CURIOSITY.name).show(
                        supportFragmentManager,
                        "Filter Popup Window"
                    )
                    1 -> FilterWindow(OPPORTUNITY.name).show(
                        supportFragmentManager,
                        "Filter Popup Window"
                    )
                    2 -> FilterWindow(SPIRIT.name).show(
                        supportFragmentManager,
                        "Filter Popup Window"
                    )
                }
            }
        }
    }
}