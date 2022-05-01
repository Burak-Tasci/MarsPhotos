package com.tsci.marsphotostask.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.tsci.marsphotostask.common.Constants
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

internal class CuriosityFragment: BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch{
            viewModel.getPhotos(Constants.Rovers.CURIOSITY.name).collect{ pagingData ->
                mAdapter.submitData(pagingData)
            }
        }


    }
}