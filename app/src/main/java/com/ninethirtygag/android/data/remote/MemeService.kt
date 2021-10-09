package com.ninethirtygag.android.data.remote

import com.ninethirtygag.android.BuildConfig
import com.ninethirtygag.android.data.remote.response.MemeRes
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface MemeService {

    @GET("cats")
    suspend fun getMemes(@Query("limit") limit: Int = 30): Response<List<MemeRes>>

    companion object {

        const val BASE_URL = "https://cataas.com/"
        private const val API_SUFFIX = "api/"

        fun create(): MemeService {
            val interceptor = HttpLoggingInterceptor()

            interceptor.setLevel(
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            )

            val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL + API_SUFFIX)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

            return retrofit.create(MemeService::class.java)
        }
    }
}