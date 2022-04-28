package com.tsci.marsphotostask.di

import com.tsci.marsphotostask.common.Constants.BASE_URL
import com.tsci.marsphotostask.data.remote.MarsPhotoApi
import com.tsci.marsphotostask.data.repository.MarsPhotoRepositoryImpl
import com.tsci.marsphotostask.domain.repository.MarsPhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMarsPhotoApi(): MarsPhotoApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarsPhotoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMarsPhotoRepository(api: MarsPhotoApi): MarsPhotoRepository{
        return MarsPhotoRepositoryImpl(api)
    }
}