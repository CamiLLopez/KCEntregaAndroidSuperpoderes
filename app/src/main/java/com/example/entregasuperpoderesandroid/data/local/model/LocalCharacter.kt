package com.example.entregasuperpoderesandroid.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")

data class LocalCharacter(

    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "photo") val photo: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "favorite") val favorite: Boolean = false
)
