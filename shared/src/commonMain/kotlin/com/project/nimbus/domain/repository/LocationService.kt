package com.project.nimbus.domain.repository

import com.project.nimbus.domain.model.LocationCoordinates

interface LocationService {
    suspend fun getCurrentLocation(): LocationCoordinates?
}