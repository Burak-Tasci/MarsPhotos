package com.tsci.marsphotostask.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.tsci.marsphotostask.common.Constants
import kotlinx.coroutines.launch

internal class OpportunityFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.filters.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                viewModel.getPhotos(
                    roverName = Constants.Rovers.OPPORTUNITY.name,
                    filters = it ?: emptyList()
                ).collect{
                    mAdapter.submitData(pagingData = it)
                }
            }
        }

    }
}