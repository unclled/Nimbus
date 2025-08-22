package com.project.nimbus.domain.location

import android.content.Context
import android.location.Geocoder
import com.project.nimbus.domain.model.AddressData
import com.project.nimbus.domain.repository.GeocoderService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale

class AndroidGeocoderService(
    private val context: Context
) : GeocoderService {

    private val geocoder = Geocoder(context, Locale.getDefault())

    override suspend fun getAddressFromCoordinates(lat: Double, lon: Double): AddressData? {
        return withContext(Dispatchers.IO) {
            try {
                val addresses = geocoder.getFromLocation(lat, lon, 1)
                addresses?.firstOrNull()?.let { address ->
                    AddressData(
                        country = address.countryName,
                        region = address.adminArea,
                        city = address.locality
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}