package com.tsci.marsphotostask.data.remote

import com.tsci.marsphotostask.common.Constants.API_KEY
import com.tsci.marsphotostask.data.remote.dto.MarsPhotosDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MarsPhotoApi {

    @GET("curiosity/photos")
    suspend fun getCuriosityMarsPhotos(
        @Query("sol") sol:Int,
        @Query("page") page: Int,
        @Query("api_key") api_key: String = API_KEY
    ): MarsPhotosDto
    @GET("spirit/photos")
    suspend fun getSpiritMarsPhotos(
        @Query("sol") sol:Int,
        @Query("page") page: Int,
        @Query("api_key") api_key: String = API_KEY
    ): MarsPhotosDto
    @GET("opportunity/photos")
    suspend fun getOpportunityMarsPhotos(
        @Query("sol") sol:Int,
        @Query("page") page: Int,
        @Query("api_key") api_key: String = API_KEY
    ): MarsPhotosDto

}