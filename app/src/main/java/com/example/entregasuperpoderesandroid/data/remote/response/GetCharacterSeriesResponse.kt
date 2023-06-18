package com.example.entregasuperpoderesandroid.data.remote.response

import com.squareup.moshi.Json

data class GetCharacterSeriesResponse(

    @Json(name = "data") val data: DataSeries
)

data class DataSeries(
    @Json(name = "results") val results: List<ResultsSeries>
)

data class ResultsSeries(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String? = "No Description",
    @Json(name = "startYear") val startYear: Int,
    @Json(name = "endYear") val endYear: Int,
    @Json(name = "thumbnail") val thumbnail: Thumbnail
)

data class ThumbnailSeries(
    @Json(name = "path") val path: String,
    @Json(name = "extension") val extension: String
)
