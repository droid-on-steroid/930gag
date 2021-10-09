package com.ninethirtygag.android.data

import com.ninethirtygag.android.data.models.Meme
import com.ninethirtygag.android.data.models.mapToData
import com.ninethirtygag.android.data.remote.MemeService
import com.ninethirtygag.android.utils.Resource


class MemeRepository(private val memeService: MemeService) {

    suspend fun getMemes(): Resource<List<Meme>> {
        try {
            val res = memeService.getMemes(MEME_PAGE_SIZE)
            if (res.isSuccessful) {
                val body: List<com.ninethirtygag.android.data.remote.response.MemeRes> = res.body()
                    ?: return Resource.Error(Throwable("Response is null"))
                return Resource.Success(body.map { it.mapToData })
            } else {
                return Resource.Error(Throwable(res.errorBody()?.string()))
            }
        } catch (e: Exception) {
            return Resource.Error(Throwable(e.message))
        }
    }

    companion object {
        private const val MEME_PAGE_SIZE = 30
    }

}