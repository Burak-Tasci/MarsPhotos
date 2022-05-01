package com.tsci.marsphotostask.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tsci.marsphotostask.common.Constants
import com.tsci.marsphotostask.common.Constants.Rovers.*
import com.tsci.marsphotostask.data.remote.dto.toMarsPhoto
import com.tsci.marsphotostask.domain.model.MarsPhoto
import com.tsci.marsphotostask.domain.repository.MarsPhotoRepository
import javax.inject.Inject

private const val TAG = "MarsPhotoPagingSource.kt"
internal class MarsPhotoPagingSource @Inject constructor(
    private val repository: MarsPhotoRepository,
    private val rover: Constants.Rovers
) : PagingSource<Int, MarsPhoto>() {
    override fun getRefreshKey(state: PagingState<Int, MarsPhoto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MarsPhoto> {
        return try {
            val currentPage = params.key ?: 1
            val response = when (rover) {
                CURIOSITY -> {
                    repository.getCuriosityMarsPhotos(page = currentPage).photos
                        .map { it.toMarsPhoto() }
                }
                OPPORTUNITY -> {
                    repository.getOpportunityMarsPhotos(page = currentPage).photos
                        .map { it.toMarsPhoto() }
                }
                SPIRIT -> {
                    repository.getSpiritMarsPhotos(page = currentPage).photos
                        .map { it.toMarsPhoto() }
                }
            }

            val responseData = mutableListOf<MarsPhoto>()
            responseData.addAll(response)
                Log.d(TAG, "load - ${rover.name}: $responseData")
            LoadResult.Page(
                data = responseData,
                prevKey = null,
                nextKey = currentPage.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(
                e
            )
        }
    }

}
