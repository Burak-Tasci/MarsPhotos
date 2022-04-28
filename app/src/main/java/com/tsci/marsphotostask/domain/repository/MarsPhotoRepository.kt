package com.tsci.marsphotostask.domain.repository

import com.tsci.marsphotostask.data.remote.dto.MarsPhotoDto

interface MarsPhotoRepository {

    suspend fun getCuriosityMarsPhotos(sol: Int, page: Int, api_key: String) : List<MarsPhotoDto>

    suspend fun getSpiritMarsPhotos(sol: Int, page: Int, api_key: String) : List<MarsPhotoDto>

    suspend fun getOpportunityMarsPhotos(sol: Int, page: Int, api_key: String) : List<MarsPhotoDto>


}