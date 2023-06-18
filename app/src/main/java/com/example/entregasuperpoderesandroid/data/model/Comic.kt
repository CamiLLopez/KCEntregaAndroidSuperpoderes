package com.example.entregasuperpoderesandroid.data.model

import com.example.entregasuperpoderesandroid.ui.screens.MediaItem

data class Comic (

    override var id: Int,
    override var title: String,
    override var description: String? = "No description",
    override var photo: String
): MediaItem