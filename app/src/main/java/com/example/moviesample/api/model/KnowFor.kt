package com.example.moviesample.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KnownFor (
    @Json(name = "backdrop_path")
    val backdropPath: String,

    @Json(name = "first_air_date")
    val firstAirDate: String?,

    @Json(name = "genre_ids")
    val genreIDS: List<Long>,

    val id: Long,

    @Json(name = "media_type")
    val mediaType: String,

    val name: String? = null,

    @Json(name = "origin_country")
    val originCountry: List<String>? = emptyList(),

    @Json(name = "original_language")
    val originalLanguage: String,

    @Json(name = "original_name")
    val originalName: String? = null,

    val overview: String,

    @Json(name = "poster_path")
    val posterPath: String? = null,

    @Json(name = "vote_average")
    val voteAverage: Double,

    @Json(name = "vote_count")
    val voteCount: Long,

    val adult: Boolean? = null,

    @Json(name = "original_title")
    val originalTitle: String? = null,

    @Json(name = "release_date")
    val releaseDate: String? = null,

    val title: String? = null,
    val video: Boolean? = null
)
