package com.example.entregasuperpoderesandroid.data.remote.response

import com.squareup.moshi.Json

data class GetCharacterComicsResponse (

@Json(name = "data") val data: DataComics
)

data class DataComics(
    @Json(name = "results") val results: List<ResultsComics>
)

data class ResultsComics(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String? = "No description",
    @Json(name = "thumbnail") val thumbnail: Thumbnail,
    @Json(name = "stories") val stories: Stories
)
