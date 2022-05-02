package com.tsci.marsphotostask.common

object Constants {

    const val BASE_URL: String = "https://api.nasa.gov/mars-photos/api/v1/rovers/"
    const val API_KEY: String = "WM6k2IFbd7OQlpeAXzKS19wgxM5WP03zEvh7ziRH"
    const val SOL: Int = 1000

//    WM6k2IFbd7OQlpeAXzKS19wgxM5WP03zEvh7ziRH
//    HmsWY2dljqU2cUh2aCcxO49aSFgfpS7T8wFgSfX0
//    Dz67ki2ScBpsrxLC0W2CdOxZDUDLcj0te5FSVzgk
//    uvfxlZTSwBjxhWfQ9i3ZgPGoq5bitqAfa9BLZVfk
    enum class Rovers { CURIOSITY, OPPORTUNITY, SPIRIT }
}