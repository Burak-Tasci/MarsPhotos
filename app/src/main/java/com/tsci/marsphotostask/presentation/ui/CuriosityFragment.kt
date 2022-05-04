package com.tsci.marsphotostask.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.tsci.marsphotostask.common.Constants
import com.tsci.marsphotostask.common.Constants.Rovers.CURIOSITY
import kotlinx.coroutines.launch

private const val TAG = "CuriosityFragment.kt"
class CuriosityFragment: BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setPhotos()
    }

    private fun setPhotos() {
        viewModel.cameras[CURIOSITY.name]?.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                viewModel.getPhotos(
                    rover = CURIOSITY
                ).collect {
                    mAdapter.submitData(pagingData = it)
                }
            }
        }
    }
}