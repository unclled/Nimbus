package com.project.nimbus.data.api

import kotlinx.serialization.Serializable

@Serializable
data class WeatherApiResponse(
    val temperature: Double,
    val windSpeed: Double,
    val condition: Int
)