package com.tsci.marsphotostask.domain.repository

import com.tsci.marsphotostask.common.Constants.API_KEY
import com.tsci.marsphotostask.common.Constants.SOL
import com.tsci.marsphotostask.data.remote.dto.MarsPhotosDto

interface MarsPhotoRepository {

    suspend fun getRoverMarsPhotos(
        roverName: String,
        sol: Int = SOL,
        page: Int, api_key: String = API_KEY,
        filters: List<String>
    ): MarsPhotosDto
}