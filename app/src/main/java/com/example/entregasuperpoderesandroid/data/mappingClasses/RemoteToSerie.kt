package com.example.entregasuperpoderesandroid.data.mappingClasses

import com.example.entregasuperpoderesandroid.data.model.Serie
import com.example.entregasuperpoderesandroid.data.remote.response.GetCharacterSeriesResponse
import com.example.entregasuperpoderesandroid.data.remote.response.ResultsSeries
import javax.inject.Inject

class RemoteToSerie @Inject constructor() {


    fun mapGetSeriesResponse(getCharacterSeriesResponse: GetCharacterSeriesResponse): List<Serie>{
       return getCharacterSeriesResponse.data.results.map {
            mapGetSerieResponse(it)
        }
    }

    private fun mapGetSerieResponse(getCharacterSerieResponse: ResultsSeries) : Serie {
        return  Serie(getCharacterSerieResponse.id, getCharacterSerieResponse.title,
            getCharacterSerieResponse.description, getCharacterSerieResponse.startYear,
            getCharacterSerieResponse.endYear, getCharacterSerieResponse.thumbnail.path + "." + getCharacterSerieResponse.thumbnail.extension)
    }
}