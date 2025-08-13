enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Nimbus"

include(":androidApp")
include(":shared")

include(":core:ui")
include(":core:navigation")

include(":feature:auth")
include(":feature:weather")

