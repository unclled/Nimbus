package com.project.nimbus.data.api

import kotlinx.serialization.Serializable

//https://open-meteo.com/en/docs?forecast_days=1&hourly=temperature_2m,wind_speed_10m,precipitation,weather_code&bounding_box=-90,-180,90,180
@Serializable
data class OpenMeteoResponse(
    val temperature: Double,
    val windSpeed: Double,
    val condition: Int
)