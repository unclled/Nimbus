package com.project.nimbus.presentation

import com.project.nimbus.domain.model.ConsensusWeather

data class WeatherState(
    val isLoading: Boolean = false,
    val weather: ConsensusWeather? = null,
    val error: String? = null,
    val address: String? = null
)
