package com.example.moviesample.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonDetail(
    val birthday: String?,
    val known_for_department: String,
    val place_of_birth: String?,
    val also_known_as: List<String>?,
    val biography: String
)
