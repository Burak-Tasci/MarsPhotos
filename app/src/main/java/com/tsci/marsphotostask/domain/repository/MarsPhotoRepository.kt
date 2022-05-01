package com.tsci.marsphotostask.domain.repository

import com.tsci.marsphotostask.common.Constants.API_KEY
import com.tsci.marsphotostask.data.remote.dto.MarsPhotosDto

interface MarsPhotoRepository {

    suspend fun getCuriosityMarsPhotos(sol: Int , page: Int, api_key: String = API_KEY) : MarsPhotosDto

    suspend fun getSpiritMarsPhotos(sol: Int , page: Int, api_key: String = API_KEY) : MarsPhotosDto

    suspend fun getOpportunityMarsPhotos(sol: Int , page: Int, api_key: String = API_KEY) : MarsPhotosDto


}