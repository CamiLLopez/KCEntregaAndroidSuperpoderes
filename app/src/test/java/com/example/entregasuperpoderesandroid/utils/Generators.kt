package com.example.entregasuperpoderesandroid.utils

import com.example.entregasuperpoderesandroid.data.model.Comic
import com.example.entregasuperpoderesandroid.data.model.Serie

fun generateSeriesList(length: Int): List<Serie> {

    return (0 until length).map {
        Serie(
            it,
            "title $it",
            "Description",
            2020,
            2021,
            "photo"
        )
    }
}
fun generateComicList(length: Int): List<Comic> {

    return (0 until length).map{
        Comic(
        it,
        "title $it",
        "Description",
        "photo"
    )}
}
