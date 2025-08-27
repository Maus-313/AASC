package com.example.aasc.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object VisionClient {
    private val ok = OkHttpClient.Builder()
        .callTimeout(30, TimeUnit.SECONDS)
        .build()

    val api: VisionService = Retrofit.Builder()
        .baseUrl("https://vision.googleapis.com/")
        .client(ok)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(VisionService::class.java)
}
