package com.tsci.marsphotostask.data.repository

import com.tsci.marsphotostask.data.remote.MarsPhotoApi
import com.tsci.marsphotostask.data.remote.dto.MarsPhotosDto
import com.tsci.marsphotostask.domain.repository.MarsPhotoRepository
import javax.inject.Inject

internal class MarsPhotoRepositoryImpl @Inject constructor(
    private val api: MarsPhotoApi
) : MarsPhotoRepository{
    override suspend fun getRoverMarsPhotos(
        roverName: String,
        sol: Int,
        page: Int,
        api_key: String
    ): MarsPhotosDto = api.getRoverMarsPhotos(roverName = roverName, page = page)

}