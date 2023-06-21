package com.example.entregasuperpoderesandroid.data.remote

import com.example.entregasuperpoderesandroid.BuildConfig
import com.example.entregasuperpoderesandroid.data.remote.response.GetCharacterComicsResponse
import com.example.entregasuperpoderesandroid.data.remote.response.GetCharacterResponse
import com.example.entregasuperpoderesandroid.data.remote.response.GetCharacterSeriesResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(private val api: IMarvelAPI): RemoteDataSource {

    private val apiKey: String = BuildConfig.API_KEY_MARVEL
    private val hash: String = BuildConfig.HASH
    private val ts: String = BuildConfig.TS

    override suspend fun getHeros(): GetCharacterResponse {
        return api.getCharacteres(apiKey, ts, hash)
    }
    override suspend fun getSeriesByHero(characterID: Int): GetCharacterSeriesResponse {
        return api.getCharactersSeries(characterID, apiKey, ts, hash)
    }

    override suspend fun getComicsByHero(characterID: Int): GetCharacterComicsResponse {
        return api.getCharactersComics(characterID, apiKey, ts, hash)
    }

}