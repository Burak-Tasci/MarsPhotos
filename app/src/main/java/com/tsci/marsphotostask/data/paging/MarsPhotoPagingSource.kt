package com.tsci.marsphotostask.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tsci.marsphotostask.common.Constants
import com.tsci.marsphotostask.common.Constants.Rovers.*
import com.tsci.marsphotostask.data.remote.MarsPhotoApi
import com.tsci.marsphotostask.data.remote.dto.toMarsPhoto
import com.tsci.marsphotostask.domain.model.MarsPhoto
import java.lang.Exception

class MarsPhotoPagingSource(
    private val marsPhotoApi: MarsPhotoApi,
    private val rover: Constants.Rovers
) : PagingSource<Int, MarsPhoto>() {
    override fun getRefreshKey(state: PagingState<Int, MarsPhoto>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MarsPhoto> {

        return try {
            val currentPage = params.key ?: 1
            val responseData = when (rover) {
                CURIOUSITY -> {
                    marsPhotoApi.getCuriosityMarsPhotos(page = currentPage).map { it.toMarsPhoto() }
                }
                OPPORTUNITY -> {
                    marsPhotoApi.getOpportunityMarsPhotos(page = currentPage)
                        .map { it.toMarsPhoto() }
                }
                SPIRIT -> {
                    marsPhotoApi.getSpiritMarsPhotos(page = currentPage).map { it.toMarsPhoto() }
                }
            }

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
