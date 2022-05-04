package com.tsci.marsphotostask.data.repository

import com.tsci.marsphotostask.data.remote.MarsPhotoApi
import com.tsci.marsphotostask.data.remote.dto.MarsPhotosDto
import com.tsci.marsphotostask.data.remote.dto.Photo
import com.tsci.marsphotostask.domain.repository.MarsPhotoRepository
import javax.inject.Inject

class MarsPhotoRepositoryImpl @Inject constructor(
    private val api: MarsPhotoApi
) : MarsPhotoRepository {
    override suspend fun getRoverMarsPhotos(
        roverName: String,
        sol: Int,
        page: Int,
        api_key: String,
        filters: List<String>
    ): MarsPhotosDto {
        val photos: MutableList<Photo> = mutableListOf()
        if (filters.isEmpty()){
            photos.addAll(api.getRoverMarsPhotos(
                roverName,
                sol,
                page,
                api_key,
                camera = "all"
            ).photos)
        }
        else {
            for (filter in filters) {
                photos.addAll(
                    api.getRoverMarsPhotos(
                        roverName = roverName,
                        sol = sol,
                        page = page,
                        camera = filter
                    ).photos
                )
            }
        }
        return MarsPhotosDto(photos)
    }

}