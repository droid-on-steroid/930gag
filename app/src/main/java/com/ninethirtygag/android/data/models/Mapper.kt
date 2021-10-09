package com.ninethirtygag.android.data.models

import com.ninethirtygag.android.data.remote.response.MemeRes

val MemeRes.mapToData get() = Meme(this.tags?.joinToString().orEmpty(), url, Type.IMAGE)