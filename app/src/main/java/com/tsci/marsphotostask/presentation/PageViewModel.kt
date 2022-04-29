package com.tsci.marsphotostask.presentation

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tsci.marsphotostask.common.Constants
import com.tsci.marsphotostask.data.paging.MarsPhotoPagingSource
import com.tsci.marsphotostask.data.remote.MarsPhotoApi
import com.tsci.marsphotostask.domain.model.MarsPhoto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val TAG = "PageViewModel.kt"

@HiltViewModel
class PageViewModel @Inject constructor(
    private val apiService: MarsPhotoApi
) : ViewModel() {


    internal fun getPhotos(rover: Constants.Rovers): Flow<PagingData<MarsPhoto>> {
        Log.d(TAG, "getPhotos: $rover")
        return Pager(PagingConfig(pageSize = 25)) {
            MarsPhotoPagingSource(apiService, rover = rover)
        }.flow.cachedIn(viewModelScope)
    }
}