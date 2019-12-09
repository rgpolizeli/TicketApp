package com.rgp.ticketapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * A Session stores the token and user id obtained after authentication.
 */
@JsonClass(generateAdapter = true)
data class Session(
    @field:Json(name = "userId") val userId: String,
    @field:Json(name = "token") val token: String
)