package com.tsci.marsphotostask.data.remote.dto

data class Rover(
    val id: Int,
    val landing_date: String,
    val launch_date: String,
    val name: String,
    val status: String
)