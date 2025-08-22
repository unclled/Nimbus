package com.project.nimbus.domain.location

data class LocationCoordinates(val latitude: Double, val longitude: Double)

interface LocationService {
    suspend fun getCurrentLocation(): LocationCoordinates?
}