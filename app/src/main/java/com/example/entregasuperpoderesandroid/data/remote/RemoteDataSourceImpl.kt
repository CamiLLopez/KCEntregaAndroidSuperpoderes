package com.example.entregasuperpoderesandroid.data.remote

import com.example.entregasuperpoderesandroid.data.remote.response.GetCharacterComicsResponse
import com.example.entregasuperpoderesandroid.data.remote.response.GetCharacterResponse
import com.example.entregasuperpoderesandroid.data.remote.response.GetCharacterSeriesResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(private val api: IMarvelAPI): RemoteDataSource {

    private val apiKey = "97057c98fd5e4ae4b4a114cbdcfb170a"
    private val hash = "2c853d14267c2faa1b5546749905dae2"


    override suspend fun getHeros(): GetCharacterResponse {
        return api.getCharacteres(apiKey, 1, hash)
    }

    override suspend fun getSeriesByHero(characterID: Int): GetCharacterSeriesResponse {
        return api.getCharactersSeries(characterID, apiKey, 1, hash)
    }

    override suspend fun getComicsByHero(characterID: Int): GetCharacterComicsResponse {
        return api.getCharactersComics(characterID, apiKey, 1, hash)
    }

}