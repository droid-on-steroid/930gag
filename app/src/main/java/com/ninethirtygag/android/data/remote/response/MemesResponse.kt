package com.ninethirtygag.android.data.remote.response

import com.ninethirtygag.android.data.remote.MemeService
import com.squareup.moshi.Json

data class MemeRes(
    @Json(name = "id") val id: String? = null,
    @Json(name = "created_at") val createdAt: String? = null,
    @Json(name = "tags") val tags: List<String>? = null
) {
    val url: String get() = "${MemeService.BASE_URL}cat/$id"
}