package com.project.nimbus.domain.model

data class WeatherInfo(
    val tempCelsius: Double,
    val condition: Int,
    val windSpeed: Double,
    val source: String,
)
