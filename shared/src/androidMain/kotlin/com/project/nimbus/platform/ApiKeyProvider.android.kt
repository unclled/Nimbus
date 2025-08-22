package com.project.nimbus.platform

import com.project.nimbus.shared.BuildConfig

actual fun getApiKey(keyName: String): String {
    return when (keyName) {
        "OPENWEATHERMAP_API_KEY" -> BuildConfig.OPENWEATHERMAP_API_KEY
        "WEATHERAPI_API_KEY" -> BuildConfig.WEATHERAPI_API_KEY
        else -> {
            println("Warning: API key '$keyName' not found in BuildConfig.")
            ""
        }
    }
}