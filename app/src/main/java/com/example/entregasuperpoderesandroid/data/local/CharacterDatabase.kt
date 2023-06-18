package com.example.entregasuperpoderesandroid.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.entregasuperpoderesandroid.data.local.model.LocalCharacter


@Database(entities = [LocalCharacter::class], version = 1)
abstract class CharacterDatabase: RoomDatabase() {

    abstract fun characterDao(): ICharacterDao

}