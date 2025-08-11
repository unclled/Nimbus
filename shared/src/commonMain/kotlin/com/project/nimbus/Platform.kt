package com.project.nimbus

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform