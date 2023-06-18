package com.example.entregasuperpoderesandroid.data.local

import androidx.compose.runtime.remember
import com.example.entregasuperpoderesandroid.data.local.model.LocalCharacter
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val dao: ICharacterDao): ILocalDataSource {
    override suspend fun getCharacters(): List<LocalCharacter> {
        return dao.getAll()
    }

    override suspend fun getCharacter(characterID: Int): LocalCharacter {
        return dao.getCharacterByID(characterID)
    }

    override suspend fun updateCharacter(characterID: Int, favorite: Boolean) {
        dao.updateHero(characterID, favorite)
    }

    override suspend fun insertCharacter(localCharacter: LocalCharacter) {
        dao.insertAllList(listOf(localCharacter))
    }

    override suspend fun insertCharacters(localCharacter: List<LocalCharacter>) {
       dao.insertAllList(localCharacter)
    }
}