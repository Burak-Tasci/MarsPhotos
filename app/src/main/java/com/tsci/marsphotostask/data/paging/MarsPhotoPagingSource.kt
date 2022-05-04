package com.tsci.marsphotostask.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tsci.marsphotostask.common.Constants
import com.tsci.marsphotostask.data.remote.dto.toMarsPhoto
import com.tsci.marsphotostask.domain.model.MarsPhoto
import com.tsci.marsphotostask.domain.repository.MarsPhotoRepository
import javax.inject.Inject

private const val TAG = "MarsPhotoPagingSource.kt"

class MarsPhotoPagingSource @Inject constructor(
    private val repository: MarsPhotoRepository,
    private val rover: Constants.Rovers,
    private val filters: List<String>
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
            val response = repository.getRoverMarsPhotos(
                roverName = rover.name,
                page = currentPage,
                filters = filters
            ).photos.map { it.toMarsPhoto() }

            val responseData = mutableListOf<MarsPhoto>()
            responseData.addAll(response)


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
