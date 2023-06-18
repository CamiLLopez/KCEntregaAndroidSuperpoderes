package com.example.entregasuperpoderesandroid.data.remote.response

import com.squareup.moshi.Json

data class GetCharacterResponse(

    @Json(name = "data") val data: Data
)
data class Data(
    @Json(name = "results") val results: List<Results>
)

data class Results(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "thumbnail") val thumbnail: Thumbnail
)

data class Thumbnail(
    @Json(name = "path") val path: String,
    @Json(name = "extension") val extension: String
)