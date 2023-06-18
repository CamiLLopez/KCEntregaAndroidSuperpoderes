package com.example.entregasuperpoderesandroid.data.remote

import com.example.entregasuperpoderesandroid.data.remote.response.GetCharacterComicsResponse
import com.example.entregasuperpoderesandroid.data.remote.response.GetCharacterResponse
import com.example.entregasuperpoderesandroid.data.remote.response.GetCharacterSeriesResponse

interface RemoteDataSource {


    suspend fun getHeros(): GetCharacterResponse

    suspend fun getSeriesByHero(characterID: Int): GetCharacterSeriesResponse

    suspend fun getComicsByHero(characterID: Int): GetCharacterComicsResponse
}