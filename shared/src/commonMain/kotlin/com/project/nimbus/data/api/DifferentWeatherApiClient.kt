package com.project.nimbus.data.api

import com.project.nimbus.platform.getApiKey
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class DifferentWeatherApiClient(
    private val httpClient: HttpClient
) {
    private val openWeatherApiKey = getApiKey("OPENWEATHERMAP_API_KEY")
    private val weatherApiKey = getApiKey("WEATHERAPI_API_KEY")
    suspend fun getOpenWeatherMapData(lat: Double, lon: Double): OpenWeatherMapResponse {
        return httpClient.get("https://api.openweathermap.org/data/2.5/weather") {
            parameter("lat", lat)
            parameter("lon", lon)
            parameter("appid", openWeatherApiKey)
            parameter("units", "metric")
        }.body<OpenWeatherMapResponse>()
    }

    suspend fun getWeatherApiData(lat: Double, lon: Double): WeatherApiResponse {
        return httpClient.get("http://api.weatherapi.com/v1/current.json?") {
            parameter("key", weatherApiKey)
            parameter("q", "$lat,$lon")
        } .body<WeatherApiResponse>()
    }

    suspend fun getOpenMeteoData(lat: Double, lon: Double): OpenMeteoResponse {
        return httpClient.get("https://api.open-meteo.com/v1/forecast?") {
            parameter("latitude", lat)
            parameter("longitude", lon)
            parameter("current_weather", true)
            parameter("hourly", "temperature_2m,wind_speed_10m,weather_code")
            parameter("forecast_days", 1)
        } .body<OpenMeteoResponse>()
    }
}