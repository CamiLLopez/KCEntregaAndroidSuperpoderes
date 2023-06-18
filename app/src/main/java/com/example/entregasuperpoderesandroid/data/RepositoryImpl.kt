package com.example.entregasuperpoderesandroid.data

import com.example.entregasuperpoderesandroid.data.model.Comic
import com.example.entregasuperpoderesandroid.data.model.Serie
import com.example.entregasuperpoderesandroid.data.model.SuperHeroCharacter
import com.example.entregasuperpoderesandroid.data.local.ILocalDataSource
import com.example.entregasuperpoderesandroid.data.mappingClasses.LocalToSuperHeroCharacter
import com.example.entregasuperpoderesandroid.data.mappingClasses.RemoteToComic
import com.example.entregasuperpoderesandroid.data.mappingClasses.RemoteToLocalCharacter
import com.example.entregasuperpoderesandroid.data.mappingClasses.RemoteToSerie
import com.example.entregasuperpoderesandroid.data.remote.RemoteDataSource
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: ILocalDataSource,
    private val remoteToLocalCharacter: RemoteToLocalCharacter,
    private val localToSuperHeroCharacter: LocalToSuperHeroCharacter,
    private val remoteToSerie: RemoteToSerie,
    private val remoteToComic: RemoteToComic
): Repository {

    override suspend fun getHeros(): List<SuperHeroCharacter> {

        if(localDataSource.getCharacters().isEmpty()){
            val remoteCharacters = remoteDataSource.getHeros()
            localDataSource.insertCharacters(remoteToLocalCharacter.mapGetCharatersResponse(remoteCharacters))
        }
      return localToSuperHeroCharacter.mapLocalToSuperHeroCharacter(localDataSource.getCharacters())
    }

    override suspend fun getHero(heroID: Int): SuperHeroCharacter {

        val localHero = localDataSource.getCharacter(heroID)
        return localToSuperHeroCharacter.mapLocalToSuperHeroCharacter(localHero)
    }

    override suspend fun getSeriesByHero(heroID: Int): List<Serie> {
        val remoteSeriesByCharacter = remoteDataSource.getSeriesByHero(heroID)
        return remoteToSerie.mapGetSeriesResponse(remoteSeriesByCharacter)
    }

    override suspend fun getComicsByHero(heroID: Int): List<Comic> {
        val remoteComicisByCharacter = remoteDataSource.getComicsByHero(heroID)
        return remoteToComic.mapGetComicResponse(remoteComicisByCharacter)
    }

    override suspend fun markFavoriteHero(heroID: Int, favorite: Boolean) {
        localDataSource.updateCharacter(heroID, favorite)
    }
}