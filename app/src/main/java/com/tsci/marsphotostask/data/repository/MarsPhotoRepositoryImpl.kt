package com.tsci.marsphotostask.data.repository

import com.tsci.marsphotostask.data.remote.MarsPhotoApi
import com.tsci.marsphotostask.data.remote.dto.MarsPhotosDto
import com.tsci.marsphotostask.domain.repository.MarsPhotoRepository
import javax.inject.Inject

internal class MarsPhotoRepositoryImpl @Inject constructor(
    private val api: MarsPhotoApi
) : MarsPhotoRepository{
    override suspend fun getCuriosityMarsPhotos(
        sol: Int,
        page: Int,
        api_key: String
    ): MarsPhotosDto = api.getCuriosityMarsPhotos(sol = sol, page = page)

    override suspend fun getSpiritMarsPhotos(
        sol: Int,
        page: Int,
        api_key: String
    ): MarsPhotosDto =  api.getSpiritMarsPhotos(sol = sol, page = page)

    override suspend fun getOpportunityMarsPhotos(
        sol: Int,
        page: Int,
        api_key: String
    ): MarsPhotosDto = api.getOpportunityMarsPhotos(sol = sol, page = page)


}