plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.dagger.hilt)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.project.nimbus.feature.weather"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
    }

    buildFeatures {
        compose = true
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
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":shared"))

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler.ksp)
    implementation(libs.androidx.hilt.navigation.compose)

    detektPlugins(libs.staticAnalysis.detektFormatting)
    detektPlugins(libs.staticAnalysis.detektLibraries)
}