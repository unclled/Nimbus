package com.project.nimbus.domain.usecase

import com.project.nimbus.domain.model.ConsensusWeather
import com.project.nimbus.domain.model.LocationInput
import com.project.nimbus.domain.repository.WeatherRepository
import kotlin.Result

class GetConsensusWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(location: LocationInput): Result<ConsensusWeather> {
        return weatherRepository.getCurrentWeatherFromAllSources(location).map { weatherList ->
            val averageTemp = weatherList.map {
                it.tempCelsius
            }.average()
            val mostCommonCondition =
                weatherList.groupBy {
                    it.condition
                }.maxBy {
                    it.value.size
                }.key
            val averageWindSpeed = weatherList.map {
                it.windSpeed
            }.average()
            val sources = weatherList.map {
                it.source
            }
            ConsensusWeather(
                averageTempCelsius = averageTemp,
                condition = mostCommonCondition,
                averageWindSpeed = averageWindSpeed,
                sources = sources
            )
        }
    }
}