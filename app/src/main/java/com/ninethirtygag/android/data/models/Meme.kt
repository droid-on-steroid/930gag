package com.ninethirtygag.android.data.models

data class Meme(val title: String, val url: String, val type: Type)

enum class Type {
    IMAGE,
    GIF,
    VIDEO
}
