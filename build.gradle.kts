plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.dagger.hilt) apply false
    alias(libs.plugins.kotlin.ksp) apply false
    alias(libs.plugins.detekt)
}

subprojects {
    if (plugins.hasPlugin("io.gitlab.arturbosch.detekt")) {
        detekt {
            config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
            source.setFrom(files(projectDir.toPath().resolve("src")))
        }
    }
}

tasks.register("detektAll") {
    group = "verification"
    description = "Runs Detekt on all modules."
    val detektTasks = subprojects.map { it.tasks.withType<io.gitlab.arturbosch.detekt.Detekt>() }
    dependsOn(detektTasks)
}