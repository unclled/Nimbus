package com.project.nimbus.data.api

import kotlinx.serialization.Serializable

//https://openweathermap.org/current
@Serializable
data class OpenWeatherMapResponse(
    val temperature: Double,
    val windSpeed: Double,
    val condition: Int
)