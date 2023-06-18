package com.example.entregasuperpoderesandroid.data.remote

import com.example.entregasuperpoderesandroid.data.remote.response.GetCharacterComicsResponse
import com.example.entregasuperpoderesandroid.data.remote.response.GetCharacterResponse
import com.example.entregasuperpoderesandroid.data.remote.response.GetCharacterSeriesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IMarvelAPI {

    @GET("v1/public/characters")
    suspend fun getCharacteres(@Query("apikey") apiKey: String,
                               @Query("ts") ts: Int = 1,
                               @Query("hash") hashMarvel: String,
                               @Query("orderBy") order: String = "name"): GetCharacterResponse



    @GET("/v1/public/characters/{characterId}/comics")
    suspend fun getCharactersComics(@Path("characterId") characterId: Int,
                                    @Query("apikey") apiKey: String,
                                    @Query("ts") ts: Int = 1,
                                    @Query("hash") hashMarvel: String,
                                    @Query("orderBy") order: String = "onsaleDate"): GetCharacterComicsResponse


    @GET("/v1/public/characters/{characterId}/series")
    suspend fun getCharactersSeries(@Path("characterId") characterId: Int,
                                    @Query("apikey") apiKey: String,
                                    @Query("ts") ts: Int = 1,
                                    @Query("hash") hashMarvel: String,
                                    @Query("orderBy") order: String = "startYear"): GetCharacterSeriesResponse
}