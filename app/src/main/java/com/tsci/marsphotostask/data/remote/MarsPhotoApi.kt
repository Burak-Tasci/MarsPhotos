package com.tsci.marsphotostask.data.remote

import com.tsci.marsphotostask.common.Constants.API_KEY
import com.tsci.marsphotostask.data.remote.dto.ResponseApi
import retrofit2.http.GET
import retrofit2.http.Query

interface MarsPhotoApi {

    @GET("curiosity/photos")
    suspend fun getCuriosityMarsPhotos(
        @Query("sol") sol:Int = 1900,
        @Query("page") page: Int,
        @Query("api_key") api_key: String = API_KEY
    ): ResponseApi
    @GET("spirit/photos")
    suspend fun getSpiritMarsPhotos(
        @Query("sol") sol:Int = 1900,
        @Query("page") page: Int,
        @Query("api_key") api_key: String = API_KEY
    ): ResponseApi
    @GET("opportunity/photos")
    suspend fun getOpportunityMarsPhotos(
        @Query("sol") sol:Int = 1900,
        @Query("page") page: Int,
        @Query("api_key") api_key: String = API_KEY
    ): ResponseApi

}