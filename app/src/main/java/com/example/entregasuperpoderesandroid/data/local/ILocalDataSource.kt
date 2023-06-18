package com.example.entregasuperpoderesandroid.data.local

import com.example.entregasuperpoderesandroid.data.local.model.LocalCharacter

interface ILocalDataSource {

    suspend fun getCharacters(): List<LocalCharacter>

    suspend fun getCharacter(characterID: Int): LocalCharacter

    suspend fun updateCharacter(characterID: Int, favorite: Boolean)

    suspend fun insertCharacter(localCharacter: LocalCharacter)

    suspend fun insertCharacters(localCharacter: List<LocalCharacter>)
}