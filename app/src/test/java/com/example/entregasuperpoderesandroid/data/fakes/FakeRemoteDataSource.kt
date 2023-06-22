package com.example.entregasuperpoderesandroid.data.fakes

import com.example.entregasuperpoderesandroid.data.remote.RemoteDataSource
import com.example.entregasuperpoderesandroid.data.remote.response.GetCharacterComicsResponse
import com.example.entregasuperpoderesandroid.data.remote.response.GetCharacterResponse
import com.example.entregasuperpoderesandroid.data.remote.response.GetCharacterSeriesResponse

class FakeRemoteDataSource: RemoteDataSource {
    override suspend fun getHeros(): GetCharacterResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getSeriesByHero(characterID: Int): GetCharacterSeriesResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getComicsByHero(characterID: Int): GetCharacterComicsResponse {
        TODO("Not yet implemented")
    }
}