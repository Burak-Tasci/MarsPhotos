package com.tsci.marsphotostask.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.tsci.marsphotostask.common.Constants
import com.tsci.marsphotostask.common.Constants.Rovers.OPPORTUNITY
import kotlinx.coroutines.launch

class OpportunityFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setPhotos()
    }

    private fun setPhotos() {
        viewModel.cameras[OPPORTUNITY.name]?.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                viewModel.getPhotos(
                    rover = OPPORTUNITY
                ).collect {
                    mAdapter.submitData(pagingData = it)
                }
            }
        }
    }
}