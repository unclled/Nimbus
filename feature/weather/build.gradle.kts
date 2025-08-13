plugins {
    id("android.library.compose")

    alias(libs.plugins.kotlin.dagger.hilt)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.project.nimbus.feature.weather"
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":shared"))

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler.ksp)
    implementation(libs.androidx.hilt.navigation.compose)
}