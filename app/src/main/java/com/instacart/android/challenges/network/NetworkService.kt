package com.instacart.android.challenges.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {
    private val logger = HttpLoggingInterceptor()

    init {
        logger.level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttp: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

    private val retrofit: Retrofit = Builder()
            .baseUrl("https://boiling-dusk-12902.herokuapp.com/")
            .client(okHttp)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val api: NetworkApi = retrofit.create(NetworkApi::class.java)
}