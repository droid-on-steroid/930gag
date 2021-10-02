package com.ninethirtygag.android.data

import com.ninethirtygag.android.data.models.ImgFlip
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


interface MemeService {

    @GET("get_memes")
    suspend fun getMemes(): ImgFlip

    companion object {

        private const val BASE_URL = "https://api.imgflip.com/"

        fun create(): MemeService {
            val interceptor = HttpLoggingInterceptor()
            // TODO for debug only
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

            return retrofit.create(MemeService::class.java)
        }
    }
}