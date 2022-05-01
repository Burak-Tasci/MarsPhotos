package com.tsci.marsphotostask.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.tsci.marsphotostask.common.Constants
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "SpiritFragment.kt"
internal class SpiritFragment: BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch{
            viewModel.getPhotos(Constants.Rovers.SPIRIT.name).collect{ pagingData ->
                mAdapter.submitData(pagingData)
            }
            Log.d(TAG, "onViewCreated: SPIRIT OVER")
        }
    }
}