package com.rgp.ticketapp.webservice.interceptor

import okhttp3.Interceptor

/**
 * Interceptor that add token to each request header.
 */
class AuthInterceptor() : Interceptor {

    val TOKEN_TYPE: String = "Bearer "

    val tokenProvider: SessionProvider =
        SessionProvider

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request =
            request.newBuilder().header("Authorization", mountAuthorizationHeaderContent()).build()
        return chain.proceed(request)
    }

    private fun mountAuthorizationHeaderContent(): String {
        val session = tokenProvider.getSession()
        return if (session == null) {
            "$TOKEN_TYPE "
        } else {
            "$TOKEN_TYPE ${session.token}"
        }
    }
}