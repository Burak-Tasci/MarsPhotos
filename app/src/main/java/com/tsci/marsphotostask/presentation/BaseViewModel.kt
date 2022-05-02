package com.tsci.marsphotostask.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tsci.marsphotostask.data.paging.MarsPhotoPagingSource
import com.tsci.marsphotostask.domain.model.MarsPhoto
import com.tsci.marsphotostask.domain.repository.MarsPhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val TAG = "BaseViewModel.kt"

@HiltViewModel
class BaseViewModel @Inject constructor(
    private val repository: MarsPhotoRepository
) : ViewModel() {

    internal val filters: MutableLiveData<List<String>> = MutableLiveData(
        listOf(
            "FHAZ", "RHAZ", "MAST", "CHEMCAM", "MAHLI", "MARDI", "NAVCAM", "PANCAM", "MINITES"
        )
    )
    internal val pagingData: MutableLiveData<PagingData<MarsPhoto>> = MutableLiveData()

    internal fun getPhotos(roverName: String, filters: List<String>): Flow<PagingData<MarsPhoto>> {
        return  Pager(PagingConfig(pageSize = 1,
            initialLoadSize = 1,
            prefetchDistance = 1)) {
            MarsPhotoPagingSource(repository, rover = roverName, filters = filters)
        }.flow.cachedIn(viewModelScope)
    }


}