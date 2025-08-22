package com.project.nimbus.di

import com.project.nimbus.data.api.DifferentWeatherApiClient
import com.project.nimbus.data.repository.WeatherRepositoryImpl
import com.project.nimbus.domain.repository.WeatherRepository
import com.project.nimbus.domain.usecase.GetConsensusWeatherUseCase
import com.project.nimbus.platform.platformModule
import com.project.nimbus.presentation.WeatherViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    single { DifferentWeatherApiClient(get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }

    factory { GetConsensusWeatherUseCase(get()) }

    factory { WeatherViewModel(get(), get()) }
}.plus(platformModule())