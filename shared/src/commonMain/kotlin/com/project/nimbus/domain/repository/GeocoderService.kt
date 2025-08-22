package com.project.nimbus.domain.repository

import com.project.nimbus.domain.model.AddressData

interface GeocoderService {
    suspend fun getAddressFromCoordinates(lat: Double, lon: Double): AddressData?
}