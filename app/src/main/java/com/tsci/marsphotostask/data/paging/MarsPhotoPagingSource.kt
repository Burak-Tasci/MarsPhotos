package com.tsci.marsphotostask.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tsci.marsphotostask.common.Constants
import com.tsci.marsphotostask.common.Constants.Rovers.*
import com.tsci.marsphotostask.data.remote.MarsPhotoApi
import com.tsci.marsphotostask.data.remote.dto.toMarsPhoto
import com.tsci.marsphotostask.domain.model.MarsPhoto
import java.lang.Exception
import javax.inject.Inject

private const val TAG = "MarsPhotoPagingSource.kt"
class MarsPhotoPagingSource @Inject constructor(
    private val marsPhotoApi: MarsPhotoApi,
    private val rover: Constants.Rovers
) : PagingSource<Int, MarsPhoto>() {
    override fun getRefreshKey(state: PagingState<Int, MarsPhoto>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MarsPhoto> {
        Log.d(TAG, "load: ${params.key}")
        return try {
            val currentPage = params.key ?: 1
            val responseData = when (rover) {
                CURIOUSITY -> {
                    marsPhotoApi.getCuriosityMarsPhotos(page = currentPage).photos.map { it.toMarsPhoto() }
                }
                OPPORTUNITY -> {
                    marsPhotoApi.getOpportunityMarsPhotos(page = currentPage).photos
                        .map { it.toMarsPhoto() }
                }
                SPIRIT -> {
                    marsPhotoApi.getSpiritMarsPhotos(page = currentPage).photos.map { it.toMarsPhoto() }
                }
            }
            Log.d(TAG, "load: $responseData")

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(
                e
            )
        }
    }

}
