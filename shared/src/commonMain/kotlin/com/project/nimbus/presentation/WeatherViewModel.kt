package com.project.nimbus.presentation

import com.project.nimbus.domain.location.LocationService
import com.project.nimbus.domain.model.LocationInput
import com.project.nimbus.domain.usecase.GetConsensusWeatherUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getConsensusWeatherUseCase: GetConsensusWeatherUseCase,
    private val locationService: LocationService
) : ViewModel() {
    private val _state = MutableStateFlow(WeatherState())
    val state: StateFlow<WeatherState> = _state.asStateFlow()

    fun loadWeather() {
        viewModelScope.launch {
            _state.value = WeatherState(isLoading = true)

            val userLocation = locationService.getCurrentLocation()
            if (userLocation != null) {
                val locationInput =
                    LocationInput.Coordinates(userLocation.latitude, userLocation.longitude)

                getConsensusWeatherUseCase(locationInput)
                    .onSuccess { weather ->
                        _state.value = WeatherState(weather = weather)
                    }
                    .onFailure { error ->
                        _state.value = WeatherState(error = error.message)
                    }
            } else {
                _state.value = WeatherState(error = "Не удалось получить геолокацию")
            }
        }
    }
}