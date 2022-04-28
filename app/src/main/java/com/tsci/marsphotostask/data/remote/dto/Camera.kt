package com.tsci.marsphotostask.data.remote.dto

internal data class Camera(
    val full_name: String,
    val id: Int,
    val name: String, // **
    val rover_id: Int
)