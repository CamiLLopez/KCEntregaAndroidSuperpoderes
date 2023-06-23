package com.example.entregasuperpoderesandroid.data.fakes

import com.example.entregasuperpoderesandroid.data.local.ILocalDataSource
import com.example.entregasuperpoderesandroid.data.local.model.LocalCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLocalDataSource: ILocalDataSource {

    override suspend fun getCharacters(): Flow<List<LocalCharacter>> {
       return flow{
           emit(listOf(LocalCharacter(123, "Test", "Test", "", false)))
       }
    }
    override suspend fun getCharacter(characterID: Int): Flow<LocalCharacter> {
        return if (characterID == 123){
            flow {}
        }else{
            flow {
                emit(LocalCharacter(1234, "Test", "", "",false))
            }
        }
    }

    override suspend fun updateCharacter(characterID: Int, favorite: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun insertCharacter(localCharacter: LocalCharacter) {
        TODO("Not yet implemented")
    }

    override suspend fun insertCharacters(localCharacters: List<LocalCharacter>) {
        TODO("Not yet implemented")
    }
}