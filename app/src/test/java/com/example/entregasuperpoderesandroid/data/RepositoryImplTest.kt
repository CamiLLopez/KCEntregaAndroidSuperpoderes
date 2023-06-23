package com.example.entregasuperpoderesandroid.data

import com.example.entregasuperpoderesandroid.data.fakes.FakeLocalDataSource
import com.example.entregasuperpoderesandroid.data.local.ILocalDataSource
import com.example.entregasuperpoderesandroid.data.mappingClasses.LocalToSuperHeroCharacter
import com.example.entregasuperpoderesandroid.data.mappingClasses.RemoteToComic
import com.example.entregasuperpoderesandroid.data.mappingClasses.RemoteToLocalCharacter
import com.example.entregasuperpoderesandroid.data.mappingClasses.RemoteToSerie
import com.example.entregasuperpoderesandroid.data.model.SuperHeroCharacter
import com.example.entregasuperpoderesandroid.data.remote.RemoteDataSource
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class RepositoryImplTest {

    private lateinit var repository: RepositoryImpl

    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var localDataSource: ILocalDataSource
    private val remoteToLocalCharacter = RemoteToLocalCharacter()
    private val localToSuperHeroCharacter = LocalToSuperHeroCharacter()
    private val remoteToSerie = RemoteToSerie()
    private val remoteToComic = RemoteToComic()

    @Before
    fun setUp() {
        remoteDataSource = mockk()
        localDataSource = FakeLocalDataSource()

        repository = RepositoryImpl(
            remoteDataSource,
            localDataSource,
            remoteToLocalCharacter,
            localToSuperHeroCharacter,
            remoteToSerie,
            remoteToComic)
    }

    @Test
    fun `WHEN getHeros secondTime successful EXPECTS list not empty from localDataSource`() = runTest {

        val herosFromLocal = repository.getHeros().toList()

        assert(herosFromLocal.isNotEmpty())
    }
    @Test
    fun `WHEN getHero by ID (123) failed EXPECTS a NO HERO from localDataSource`() = runTest {

        val heroByID = repository.getHero(123).toList()

        assert(heroByID.isNullOrEmpty())
    }

    @Test
    fun `WHEN getHero by ID successful EXPECTS a HERO from localDataSource`() = runTest {

        val heroByID = repository.getHero(1234).toList()

        val hero = heroByID.first()
        assert(hero.name.isNotBlank())
        assert(heroByID.isNotEmpty())
    }
}