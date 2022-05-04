package com.tsci.marsphotostask.presentation

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tsci.marsphotostask.common.Constants
import com.tsci.marsphotostask.common.Constants.Rovers.*
import com.tsci.marsphotostask.data.paging.MarsPhotoPagingSource
import com.tsci.marsphotostask.domain.model.MarsPhoto
import com.tsci.marsphotostask.domain.repository.MarsPhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val TAG = "MainViewModel.kt"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MarsPhotoRepository
) : ViewModel() {


    val cameras: MutableMap<String, MutableLiveData<List<String>>> = mutableMapOf(
        CURIOSITY.name to MutableLiveData<List<String>>(
            listOf(
                "FHAZ", "RHAZ", "MAST", "CHEMCAM", "MAHLI", "MARDI", "NAVCAM"
            )
        ),
        OPPORTUNITY.name to MutableLiveData<List<String>>(
            listOf(
                "FHAZ", "RHAZ", "NAVCAM", "PANCAM", "MINITES"
            )
        ),
        SPIRIT.name to MutableLiveData<List<String>>(
            listOf(
                "FHAZ", "RHAZ", "NAVCAM", "PANCAM", "MINITES"
            )
        )
    )

    fun getPhotos(rover: Constants.Rovers): Flow<PagingData<MarsPhoto>> {
        return Pager(
            PagingConfig(
                pageSize = 1,
                initialLoadSize = 1,
                prefetchDistance = 1
            )
        ) {
            MarsPhotoPagingSource(repository, rover = rover, filters = cameras[rover.name]?.value ?: emptyList())
        }.flow.cachedIn(viewModelScope)
    }
}