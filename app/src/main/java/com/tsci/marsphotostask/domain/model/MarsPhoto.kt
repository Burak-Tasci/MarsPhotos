package com.tsci.marsphotostask.domain.model

data class MarsPhoto(
    val id: Int,
    val cameraName: String,
    val roverName: String,
    val imgSrc: String,
    val earthDate: String,
    val launchDate: String,
    val landingDate: String,
    val status: String
)