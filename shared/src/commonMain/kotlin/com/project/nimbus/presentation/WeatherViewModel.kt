package com.project.nimbus.presentation

import com.project.nimbus.domain.model.LocationInput
import com.project.nimbus.domain.repository.GeocoderService
import com.project.nimbus.domain.repository.LocationService
import com.project.nimbus.domain.usecase.GetConsensusWeatherUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getConsensusWeatherUseCase: GetConsensusWeatherUseCase,
    private val locationService: LocationService,
    private val geocoderService: GeocoderService
) : ViewModel() {
    private val _state = MutableStateFlow(WeatherState())
    val state: StateFlow<WeatherState> = _state.asStateFlow()

    fun loadWeather() {
        viewModelScope.launch {
            _state.value = WeatherState(isLoading = true)
            val userLocation = locationService.getCurrentLocation()
            if (userLocation != null) {
                val addressJob = async {
                    geocoderService.getAddressFromCoordinates(userLocation.latitude, userLocation.longitude)
                }
                val weatherJob = async {
                    val locationInput = LocationInput.Coordinates(userLocation.latitude, userLocation.longitude)
                    getConsensusWeatherUseCase(locationInput)
                }

                val addressData = addressJob.await()
                val weatherResult = weatherJob.await()

                val formattedAddress = if (addressData != null) {
                    listOfNotNull(
                        addressData.country,
                        addressData.region,
                        addressData.city
                    ).joinToString(", ")
                } else {
                    "Местоположение определено"
                }

                val finalAddress = formattedAddress.ifBlank { "Местоположение определено" }

                weatherResult
                    .onSuccess { weather ->
                        _state.value = WeatherState(weather = weather, address = finalAddress)
                    }
                    .onFailure { error ->
                        _state.value = WeatherState(error = error.message, address = finalAddress)
                    }
            } else {
                _state.value = WeatherState(error = "Ошибка при получении локации")
            }
        }
    }
}