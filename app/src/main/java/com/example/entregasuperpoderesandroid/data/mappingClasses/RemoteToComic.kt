package com.example.entregasuperpoderesandroid.data.mappingClasses

import com.example.entregasuperpoderesandroid.data.model.Comic
import com.example.entregasuperpoderesandroid.data.remote.response.GetCharacterComicsResponse
import com.example.entregasuperpoderesandroid.data.remote.response.ResultsComics
import javax.inject.Inject

class RemoteToComic @Inject constructor() {


    fun mapGetComicResponse(getCharacterComicsResponse: GetCharacterComicsResponse): List<Comic>{
        return getCharacterComicsResponse.data.results.map {
            mapGetComicResponse(it)
        }
    }

    private fun mapGetComicResponse(getCharacterComicResponse: ResultsComics) : Comic {
        return  Comic(getCharacterComicResponse.id, getCharacterComicResponse.title, getCharacterComicResponse.description,
            getCharacterComicResponse.thumbnail.path + "." + getCharacterComicResponse.thumbnail.extension)
    }
}