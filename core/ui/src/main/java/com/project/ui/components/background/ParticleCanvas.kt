package com.project.ui.components.background

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import com.project.ui.theme.NimbusTheme
import com.project.ui.utils.Constants
import kotlin.random.Random

@Composable
fun ParticleCanvas(
    modifier: Modifier = Modifier,
    particleCount: Int = Constants.PARTICLE_COUNT,
    particleColor: Color = NimbusTheme.colors.accentColor,
    particleMinRadius: Float = Constants.PARTICLE_MIN_RADIUS,
    particleMaxRadius: Float = Constants.PARTICLE_MAX_RADIUS,
    particleMinSpeed: Float = Constants.PARTICLE_MIN_SPEED,
    particleMaxSpeed: Float = Constants.PARTICLE_MAX_SPEED
) {
    val particles = remember { mutableStateListOf<Particle>() }
    var canvasSize by remember { mutableStateOf(IntSize.Zero) }

    var trigger by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(canvasSize) {
        if (canvasSize == IntSize.Zero) return@LaunchedEffect

        particles.clear()
        for (i in 0 until particleCount) {
            particles.add(
                Particle(
                    x = Random.nextFloat() * canvasSize.width,
                    y = Random.nextFloat() * canvasSize.height,
                    radius = Random.nextFloat() * (particleMaxRadius - particleMinRadius) + particleMinRadius,
                    alpha = Random.nextFloat() * 0.5f + 0.1f,
                    velocityX = ((Random.nextFloat() * 2) - 1f) * particleMaxSpeed,
                    velocityY = ((Random.nextFloat() * 2) - 1f) * particleMaxSpeed
                ).apply {
                    if (velocityX == 0f) velocityX = (if (Random.nextBoolean()) 1 else -1) * particleMinSpeed
                    if (velocityY == 0f) velocityY = (if (Random.nextBoolean()) 1 else -1) * particleMinSpeed
                }
            )
        }

        while (true) {
            withFrameNanos { newTime ->
                particles.forEach { particle ->
                    particle.x += particle.velocityX
                    particle.y += particle.velocityY

                    if (particle.x < 0) particle.x = canvasSize.width.toFloat()
                    if (particle.x > canvasSize.width) particle.x = 0f

                    if (particle.y < 0) particle.y = canvasSize.height.toFloat()
                    if (particle.y > canvasSize.height) particle.y = 0f
                }
                // Обновляем триггер, чтобы заставить Canvas перерисоваться
                trigger = newTime / 1_000_000_000f // Используем время кадра
            }
        }
    }

    Canvas(modifier = modifier) {
        val a = trigger

        if (size.width.toInt() != canvasSize.width || size.height.toInt() != canvasSize.height) {
            canvasSize = IntSize(size.width.toInt(), size.height.toInt())
        }

        particles.forEach { particle ->
            drawCircle(
                color = particleColor.copy(alpha = particle.alpha),
                center = Offset(particle.x, particle.y),
                radius = particle.radius
            )
        }
    }
}

private data class Particle(
    var x: Float,
    var y: Float,
    var radius: Float,
    var alpha: Float,
    var velocityX: Float,
    var velocityY: Float
)