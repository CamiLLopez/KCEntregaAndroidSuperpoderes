package com.example.entregasuperpoderesandroid.utils

import android.content.Context
import androidx.room.Room
import com.example.entregasuperpoderesandroid.data.local.CharacterDatabase
import com.example.entregasuperpoderesandroid.data.local.ICharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun providesSupercharacterDatabase(@ApplicationContext context: Context): CharacterDatabase {
        val db = Room.databaseBuilder(
            context,
            CharacterDatabase::class.java, "superhero-db"
        ).build()
        return db
    }

    @Provides
    fun providesDao(db: CharacterDatabase): ICharacterDao {
        val dao = db.characterDao()
        return dao
    }
}