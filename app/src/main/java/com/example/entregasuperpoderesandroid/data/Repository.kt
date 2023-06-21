package com.example.entregasuperpoderesandroid.data

import com.example.entregasuperpoderesandroid.data.model.Comic
import com.example.entregasuperpoderesandroid.data.model.Serie
import com.example.entregasuperpoderesandroid.data.model.SuperHeroCharacter
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getHeros(): Flow<List<SuperHeroCharacter>>

    suspend fun getHero(heroID: Int): SuperHeroCharacter

    suspend fun getSeriesByHero(heroID: Int): List<Serie>

    suspend fun getComicsByHero(heroID: Int): List<Comic>

    suspend fun insertHero(hero: SuperHeroCharacter)

    suspend fun markFavoriteHero(heroID: Int, favorite: Boolean)
}