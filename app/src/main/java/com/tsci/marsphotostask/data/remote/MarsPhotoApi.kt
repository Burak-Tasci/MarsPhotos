package com.tsci.marsphotostask.data.remote

import com.tsci.marsphotostask.common.Constants.API_KEY
import com.tsci.marsphotostask.common.Constants.SOL
import com.tsci.marsphotostask.data.remote.dto.MarsPhotosDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarsPhotoApi {

    @GET("{roverName}/photos")
    suspend fun getRoverMarsPhotos(
        @Path("roverName") roverName: String,
        @Query("sol") sol:Int = SOL,
        @Query("page") page: Int,
        @Query("api_key") api_key: String = API_KEY,
        @Query("camera") camera: String
    ): MarsPhotosDto

}