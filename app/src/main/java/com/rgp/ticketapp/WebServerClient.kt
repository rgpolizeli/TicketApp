package com.rgp.ticketapp

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Client of the REST Web Server.
 */
object WebServerClient {

    private val BASE_URL: String = "http://10.0.2.2:3000/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val authenticationAPI: AuthenticationAPI = retrofit.create(AuthenticationAPI::class.java)
}