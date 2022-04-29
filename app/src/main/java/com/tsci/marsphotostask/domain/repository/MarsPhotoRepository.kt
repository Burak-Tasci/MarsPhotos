package com.tsci.marsphotostask.domain.repository

import com.tsci.marsphotostask.data.remote.dto.ResponseApi

interface MarsPhotoRepository {

    suspend fun getCuriosityMarsPhotos(sol: Int, page: Int, api_key: String) : ResponseApi

    suspend fun getSpiritMarsPhotos(sol: Int, page: Int, api_key: String) : ResponseApi

    suspend fun getOpportunityMarsPhotos(sol: Int, page: Int, api_key: String) : ResponseApi


}