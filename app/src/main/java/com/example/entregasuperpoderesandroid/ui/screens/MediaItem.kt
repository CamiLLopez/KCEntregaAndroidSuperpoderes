package com.example.entregasuperpoderesandroid.ui.screens

import android.content.ClipData.Item

interface MediaItem {

    val id: Int
    val title: String
    val description: String?
        get() = "No description"
    val photo: String
    val storyName: String
}