package com.project.nimbus.domain.model

data class ConsensusWeather(
    val averageTempCelsius: Double,
    val condition: Int,
    val averageWindSpeed: Double,
    val sources: List<String>
)
