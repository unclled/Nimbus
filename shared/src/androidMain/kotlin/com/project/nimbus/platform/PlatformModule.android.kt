package com.project.nimbus.platform

import com.project.nimbus.domain.location.AndroidLocationService
import com.project.nimbus.domain.location.LocationService
import org.koin.dsl.module

actual fun platformModule() = module {
    single<LocationService> { AndroidLocationService(get()) }
}