package com.tsci.marsphotostask.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.tsci.marsphotostask.common.Constants.Rovers.SPIRIT
import kotlinx.coroutines.launch

private const val TAG = "SpiritFragment.kt"

class SpiritFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setPhotos()
    }

    private fun setPhotos() {
        viewModel.cameras[SPIRIT.name]?.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                viewModel.getPhotos(
                    rover = SPIRIT
                ).collect {
                    mAdapter.submitData(pagingData = it)
                }
            }
        }
    }
}