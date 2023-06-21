package com.example.entregasuperpoderesandroid.data.local

import com.example.entregasuperpoderesandroid.data.local.model.LocalCharacter
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource {

    suspend fun getCharacters(): Flow<List<LocalCharacter>>
    suspend fun getCharacter(characterID: Int): LocalCharacter

    suspend fun updateCharacter(characterID: Int, favorite: Boolean)

    suspend fun insertCharacter(localCharacter: LocalCharacter)

    suspend fun insertCharacters(localCharacters: List<LocalCharacter>)
}