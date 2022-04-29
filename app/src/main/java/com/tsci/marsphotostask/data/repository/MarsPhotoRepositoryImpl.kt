package com.tsci.marsphotostask.data.repository

import com.tsci.marsphotostask.data.remote.MarsPhotoApi
import com.tsci.marsphotostask.data.remote.dto.ResponseApi
import com.tsci.marsphotostask.domain.repository.MarsPhotoRepository
import javax.inject.Inject

class MarsPhotoRepositoryImpl @Inject constructor(
    private val api: MarsPhotoApi
) : MarsPhotoRepository{
    override suspend fun getCuriosityMarsPhotos(
        sol: Int,
        page: Int,
        api_key: String
    ): ResponseApi = api.getCuriosityMarsPhotos(page = page)

    override suspend fun getSpiritMarsPhotos(
        sol: Int,
        page: Int,
        api_key: String
    ): ResponseApi = api.getSpiritMarsPhotos(page = page)

    override suspend fun getOpportunityMarsPhotos(
        sol: Int,
        page: Int,
        api_key: String
    ): ResponseApi = api.getOpportunityMarsPhotos(page = page)


}