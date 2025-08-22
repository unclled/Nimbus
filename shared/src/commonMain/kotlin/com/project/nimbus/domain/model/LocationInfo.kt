package com.project.nimbus.domain.model

sealed class LocationInput {
    data class Coordinates(val lat: Double, val lon: Double) : LocationInput()
    data class CityName(val city: String) : LocationInput()
}