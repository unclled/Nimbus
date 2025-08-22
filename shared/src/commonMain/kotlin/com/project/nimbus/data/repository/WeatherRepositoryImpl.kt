package com.project.nimbus.data.repository

import com.project.nimbus.data.api.DifferentWeatherApiClient
import com.project.nimbus.domain.model.LocationInput
import com.project.nimbus.domain.model.WeatherInfo
import com.project.nimbus.domain.repository.WeatherRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class WeatherRepositoryImpl(
    private val apiClient: DifferentWeatherApiClient
) : WeatherRepository {
    override suspend fun getCurrentWeatherFromAllSources(location: LocationInput): Result<List<WeatherInfo>> {
        return runCatching {
            val coordinates = location as LocationInput.Coordinates
            val lat = coordinates.lat
            val lon = coordinates.lon

            coroutineScope {
                val openMeteoDeferred = async { apiClient.getOpenMeteoData(lat, lon) }
                val openWeatherDeferred = async { apiClient.getOpenWeatherMapData(lat, lon) }
                val weatherApiDeferred = async { apiClient.getWeatherApiData(lat, lon) }
                
                val openMeteoData = openMeteoDeferred.await()
                val openWeatherData = openWeatherDeferred.await()
                val weatherApiData = weatherApiDeferred.await()
                
                listOf(
                    WeatherInfo(
                        tempCelsius = openMeteoData.temperature,
                        condition = openMeteoData.condition,
                        windSpeed = openMeteoData.windSpeed,
                        source = "Open Meteo"
                    ),
                    WeatherInfo(
                        tempCelsius = openWeatherData.temperature,
                        condition = openWeatherData.condition,
                        windSpeed = openWeatherData.windSpeed,
                        source = "Open Weather"
                    ),
                    WeatherInfo(
                        tempCelsius = weatherApiData.temperature,
                        condition = weatherApiData.condition,
                        windSpeed = weatherApiData.windSpeed,
                        source = "Weather Api"
                    )
                )
            }
        }
    }
}