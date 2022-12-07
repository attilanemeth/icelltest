package com.example.moviesample.api

object GlideImageUtil {
    private const val POSTER_PATH = "https://image.tmdb.org/t/p/w342"

    @JvmStatic
    fun getPoster(posterPath: String?): String {
        return POSTER_PATH + posterPath
    }
}