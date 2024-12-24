package com.example.webviewg

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform