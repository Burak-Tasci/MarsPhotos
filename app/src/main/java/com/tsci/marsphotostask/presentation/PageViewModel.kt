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
import com.tsci.marsphotostask.data.remote.dto.Photo
import com.tsci.marsphotostask.data.remote.dto.toMarsPhoto
import com.tsci.marsphotostask.domain.model.MarsPhoto
import com.tsci.marsphotostask.domain.repository.MarsPhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val TAG = "PageViewModel.kt"

@HiltViewModel
class PageViewModel @Inject constructor(
    private val repository: MarsPhotoRepository
) : ViewModel() {

    val listData = MutableLiveData<List<MarsPhoto>>()

    internal fun getPhotos(roverName: String): Flow<PagingData<MarsPhoto>> {
        return  Pager(PagingConfig(pageSize = 1,
            initialLoadSize = 1,
            prefetchDistance = 1)) {
            MarsPhotoPagingSource(repository, rover = roverName)
        }.flow.cachedIn(viewModelScope)
    }
}