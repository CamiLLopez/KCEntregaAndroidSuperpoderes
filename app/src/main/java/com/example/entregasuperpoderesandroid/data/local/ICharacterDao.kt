package com.example.entregasuperpoderesandroid.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.entregasuperpoderesandroid.data.local.model.LocalCharacter


@Dao
interface ICharacterDao {

    @Query("SELECT * FROM character")
    suspend fun getAll(): List<LocalCharacter>

    @Query("SELECT * FROM character WHERE character.id==:idFragment")
    suspend fun getCharacterByID(idFragment: Int): LocalCharacter

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllVararg(vararg users: LocalCharacter)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllList(users: List<LocalCharacter>)

    @Query("UPDATE character SET favorite=:favorite  WHERE character.id==:id")
    suspend fun  updateHero(id: Int, favorite: Boolean)

    @Delete
    suspend fun delete(user: LocalCharacter)
}