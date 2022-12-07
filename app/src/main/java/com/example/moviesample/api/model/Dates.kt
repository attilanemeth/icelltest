package com.example.moviesample.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Dates(
    val maximum: String?,
    val minimum: String?
)