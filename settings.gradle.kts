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
include(
    ":androidApp",
    ":shared",
    ":core:ui",
    ":core:navigation",
    ":feature:auth",
    ":feature:weather"
)