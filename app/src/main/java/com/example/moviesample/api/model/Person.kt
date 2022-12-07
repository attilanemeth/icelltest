package com.example.moviesample.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Person (
    val adult: Boolean,
    val gender: Long,
    val id: Long,
    @Json(name = "known_for")
    val knownFor: List<KnownFor>,
    @Json(name = "known_for_department")
    val knownForDepartment: String,
    val name: String,
    val popularity: Double,
    @Json(name = "profile_path")
    val profilePath: String? = null
)