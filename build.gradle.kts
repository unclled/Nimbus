plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.ksp) apply false
    alias(libs.plugins.detekt)
}

allprojects {
    repositories {
        mavenCentral()
        google()
    }
}

subprojects {
    plugins.withId("io.gitlab.arturbosch.detekt") {
        dependencies {
            detektPlugins(libs.staticAnalysis.detektFormatting)
            detektPlugins(libs.staticAnalysis.detektLibraries)
        }
    }
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
    jvmTarget = "17"
}

tasks.register("detektAll") {
    group = "verification"
    description = "Runs Detekt on all modules."
    dependsOn(tasks.withType<io.gitlab.arturbosch.detekt.Detekt>())
}