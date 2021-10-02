package com.ninethirtygag.android.data.models

import com.squareup.moshi.Json

data class Data(val memes: List<Meme>? = null)

data class ImgFlip(val success: Boolean? = null, val data: Data? = null)

data class Meme(
    val id: String?,
    val name: String?,
    val url: String?,
    val width: Int?,
    val height: Int?,
    @Json(name = "box_count")
    val boxCount: Int?
)