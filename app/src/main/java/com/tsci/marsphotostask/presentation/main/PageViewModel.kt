package com.tsci.marsphotostask.presentation.main

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.tsci.marsphotostask.common.Constants
import com.tsci.marsphotostask.data.paging.MarsPhotoPagingSource
import com.tsci.marsphotostask.data.remote.MarsPhotoApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PageViewModel @Inject constructor(
    private val apiService: MarsPhotoApi
) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 1)) {
        MarsPhotoPagingSource(apiService, Constants.Rovers.CURIOUSITY)
    }.flow.cachedIn(viewModelScope)
}