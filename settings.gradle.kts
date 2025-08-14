pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
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