package com.project.nimbus.domain.repository

import com.project.nimbus.domain.model.LocationInput
import com.project.nimbus.domain.model.WeatherInfo

interface WeatherRepository {
    suspend fun getCurrentWeatherFromAllSources(location: LocationInput): Result<List<WeatherInfo>>
}