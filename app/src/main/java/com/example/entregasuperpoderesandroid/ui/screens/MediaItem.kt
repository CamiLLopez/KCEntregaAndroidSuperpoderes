package com.example.entregasuperpoderesandroid.ui.screens

interface MediaItem {

    val id: Int
    val title: String
    val description: String?
        get() = "No description"
    val photo: String
}