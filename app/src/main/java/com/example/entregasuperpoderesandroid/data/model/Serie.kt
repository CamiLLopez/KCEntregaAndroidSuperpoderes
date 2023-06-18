package com.example.entregasuperpoderesandroid.data.model

import com.example.entregasuperpoderesandroid.data.remote.response.Thumbnail
import com.example.entregasuperpoderesandroid.ui.screens.MediaItem
import com.squareup.moshi.Json

data class Serie (

    override var id: Int,
    override var title: String,
    override var description: String? = "No description",
    var startYear: Int,
    var endYear: Int,
    override var photo: String
): MediaItem
