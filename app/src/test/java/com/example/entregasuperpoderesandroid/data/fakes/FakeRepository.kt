package com.example.entregasuperpoderesandroid.data.fakes

import com.example.entregasuperpoderesandroid.data.Repository
import com.example.entregasuperpoderesandroid.data.model.Comic
import com.example.entregasuperpoderesandroid.data.model.Serie
import com.example.entregasuperpoderesandroid.data.model.SuperHeroCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository: Repository {
    override suspend fun getHeros(): Flow<List<SuperHeroCharacter>> {
        TODO("Not yet implemented")
    }

    override suspend fun getHero(heroID: Int): Flow<SuperHeroCharacter> {

        return flow{
            emit(SuperHeroCharacter(heroID, "Test", "Test", "", false))
        }
    }

    override suspend fun getSeriesByHero(heroID: Int): List<Serie> {
        return listOf(Serie(123, "title", "Description", 2020, 2021,"photo", ""))
    }

    override suspend fun getComicsByHero(heroID: Int): List<Comic> {
        return listOf(Comic(123, "title", "Description", "photo", ""))

    }

    override suspend fun insertHero(hero: SuperHeroCharacter) {
        TODO("Not yet implemented")
    }

    override suspend fun markFavoriteHero(heroID: Int, favorite: Boolean) {
        TODO("Not yet implemented")
    }
}