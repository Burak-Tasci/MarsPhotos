package com.tsci.marsphotostask.data.remote.dto

import com.tsci.marsphotostask.domain.model.MarsPhoto

data class Photo(
    val camera: Camera,
    val earth_date: String,
    val id: Int,
    val img_src: String,
    val rover: Rover,
    val sol: Int
)

fun Photo.toMarsPhoto(): MarsPhoto {
    return MarsPhoto(
        id = id,
        cameraName = camera.name,
        roverName =  rover.name,
        imgSrc = img_src,
        earthDate = earth_date,
        launchDate = rover.launch_date,
        landingDate = rover.landing_date,
        status = rover.status
    )
}