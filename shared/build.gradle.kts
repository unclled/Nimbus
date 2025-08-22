import java.util.Properties
import java.io.FileInputStream

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.project.nimbus.shared"
    compileSdk = 36

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        buildConfig = true
    }

    sourceSets.getByName("main") {
        manifest.srcFile("src/androidMain/AndroidManifest.xml")
    }

    defaultConfig {
        minSdk = 24

        val properties = Properties()
        val localPropertiesFile = rootProject.file("local.properties")

        if (localPropertiesFile.exists()) {
            properties.load(FileInputStream(localPropertiesFile))
            println("local.properties loaded successfully.")
        } else {
            println("Warning: local.properties file not found. API keys will be empty.")
        }

        val openWeatherApiKey = properties.getProperty("OPENWEATHERMAP_API_KEY") ?: ""
        val weatherApiKey = properties.getProperty("WEATHERAPI_API_KEY") ?: ""

        buildConfigField("String", "OPENWEATHERMAP_API_KEY", "\"$openWeatherApiKey\"")
        buildConfigField("String", "WEATHERAPI_API_KEY", "\"$weatherApiKey\"")
    }
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
        publishLibraryVariants("release", "debug")
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = false
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)

                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)

                implementation(libs.koin.core)
                implementation(libs.mvvm.compose)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.okhttp)
                implementation(libs.androidx.core.ktx)
                implementation(libs.play.services.location)
            }
        }
//        val iosMain by getting {
//            dependencies {
//                implementation(libs.ktor.client.darwin)
//            }
//        }
    }
}

dependencies {
    detektPlugins(libs.staticAnalysis.detektFormatting)
    detektPlugins(libs.staticAnalysis.detektLibraries)
}