package com.example.entregasuperpoderesandroid.data.mappingClasses

import com.example.entregasuperpoderesandroid.data.local.model.LocalCharacter
import com.example.entregasuperpoderesandroid.data.remote.response.GetCharacterResponse
import com.example.entregasuperpoderesandroid.data.remote.response.Results
import javax.inject.Inject

class RemoteToLocalCharacter @Inject constructor() {


    fun mapGetCharatersResponse(getCharacterResponse: GetCharacterResponse): List<LocalCharacter>{
        return getCharacterResponse.data.results.map {
            mapGetHeroResponse(it)
        }
    }

    private fun mapGetHeroResponse(getCharacterResponse: Results) : LocalCharacter {
        return  LocalCharacter(getCharacterResponse.id, getCharacterResponse.name,
            getCharacterResponse.thumbnail.path + "." + getCharacterResponse.thumbnail.extension, getCharacterResponse.description)
    }

}