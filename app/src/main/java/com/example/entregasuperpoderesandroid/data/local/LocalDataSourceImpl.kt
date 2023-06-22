package com.example.entregasuperpoderesandroid.data.local

import com.example.entregasuperpoderesandroid.data.local.model.LocalCharacter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val dao: ICharacterDao): ILocalDataSource {
    override suspend fun getCharacters(): Flow<List<LocalCharacter>> {
        return dao.getAll()
    }
    override suspend fun getCharacter(characterID: Int): Flow<LocalCharacter> {
        return dao.getCharacterByID(characterID)
    }

    override suspend fun updateCharacter(characterID: Int, favorite: Boolean) {
        dao.updateHero(characterID, favorite)
    }

    override suspend fun insertCharacter(localCharacter: LocalCharacter) {
        dao.insertAllVararg(localCharacter)
    }

    override suspend fun insertCharacters(localCharacters: List<LocalCharacter>) {
       dao.insertAllList(localCharacters)
    }
}

