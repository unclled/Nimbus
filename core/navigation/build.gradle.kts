plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.project.nimbus.core.navigation"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidx.navigation.compose)

    detektPlugins(libs.staticAnalysis.detektFormatting)
    detektPlugins(libs.staticAnalysis.detektLibraries)

    detektPlugins(libs.staticAnalysis.detektFormatting)
    detektPlugins(libs.staticAnalysis.detektLibraries)
}