package com.tsci.marsphotostask.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.tsci.marsphotostask.common.Constants.Rovers.CURIOSITY
import kotlinx.coroutines.launch

private const val TAG = "CuriosityFragment.kt"
internal class CuriosityFragment: BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.cameras[CURIOSITY.name]?.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                viewModel.getPhotos(
                    roverName = CURIOSITY.name,
                    filters = it ?: emptyList()
                ).collect{
                    mAdapter.submitData(pagingData = it)
                }
            }
        }
    }
}