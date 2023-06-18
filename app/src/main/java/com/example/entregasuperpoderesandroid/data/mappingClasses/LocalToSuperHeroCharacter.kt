package com.example.entregasuperpoderesandroid.data.mappingClasses

import com.example.entregasuperpoderesandroid.data.model.SuperHeroCharacter
import com.example.entregasuperpoderesandroid.data.local.model.LocalCharacter
import javax.inject.Inject

class LocalToSuperHeroCharacter @Inject constructor() {

    fun mapLocalToSuperHeroCharacter(localCharacters: List<LocalCharacter>): List<SuperHeroCharacter>{
        return localCharacters.map { mapLocalToSuperHeroCharacter(it) }
    }

     fun mapLocalToSuperHeroCharacter(localCharacter: LocalCharacter) : SuperHeroCharacter {
        return SuperHeroCharacter(localCharacter.id, localCharacter.name, localCharacter.description, localCharacter.photo )
    }
}