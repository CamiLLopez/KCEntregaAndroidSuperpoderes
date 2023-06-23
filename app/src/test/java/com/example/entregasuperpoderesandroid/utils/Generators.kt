package com.example.entregasuperpoderesandroid.utils

import com.example.entregasuperpoderesandroid.data.local.model.LocalCharacter
import com.example.entregasuperpoderesandroid.data.model.Comic
import com.example.entregasuperpoderesandroid.data.model.Serie
import com.example.entregasuperpoderesandroid.data.model.SuperHeroCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun generateHerosList(): Flow<List<SuperHeroCharacter>> {

    return flow{
        emit(listOf(SuperHeroCharacter(123, "Test", "Test", "", false)))
    }
}
fun generateHerosListEmpty(): Flow<List<SuperHeroCharacter>> {

    return flow{
        emit(listOf())
    }
}
fun generateComicList(): List<Comic> {

    return listOf(Comic(123, "title", "Description", "photo", ""))
}

fun generateSeriesList(): List<Serie> {

    return listOf(Serie(123, "title", "Description", 2020, 2021,"photo", ""))

}