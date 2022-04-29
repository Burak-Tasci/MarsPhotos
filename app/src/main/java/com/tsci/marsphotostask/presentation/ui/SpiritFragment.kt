package com.tsci.marsphotostask.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.tsci.marsphotostask.common.Constants
import kotlinx.coroutines.launch

internal class SpiritFragment: BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch{
            viewModel.getPhotos(Constants.Rovers.SPIRIT).collect{ pagingData ->
                mAdapter.submitData(pagingData)
            }
        }
    }
}