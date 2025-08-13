import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.the
import org.gradle.api.artifacts.VersionCatalogsExtension

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

val libs = the<VersionCatalogsExtension>().named("libs")

android {
    compileSdk = 35

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.findLibrary("androidx-core-ktx").get())
    implementation(libs.findLibrary("androidx-lifecycle-runtime-ktx").get())
    implementation(libs.findLibrary("androidx-activity-compose").get())

    implementation(platform(libs.findLibrary("androidx-compose-bom").get()))
    implementation(libs.findLibrary("androidx-ui").get())
    implementation(libs.findLibrary("androidx-ui-graphics").get())
    implementation(libs.findLibrary("androidx-ui-tooling-preview").get())
    implementation(libs.findLibrary("androidx-material3").get())
}