package com.example.entregasuperpoderesandroid.ui.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.entregasuperpoderesandroid.data.Repository
import com.example.entregasuperpoderesandroid.data.fakes.FakeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterDetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CharacterDetailViewModel

    private lateinit var repository: Repository


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        repository = FakeRepository()
        viewModel = CharacterDetailViewModel(repository)
    }

    @Test
    fun `WHEN getSeriesByHero EXPECT successful response with a list of Series`() = runTest {

        viewModel.getSeriesByHero(123)

        val stateFlow = viewModel.series.first()

        assert(stateFlow.isNotEmpty())
    }

    @Test
    fun `WHEN getComicByHero EXPECT successful response with a list of Comics`() = runTest {

        viewModel.getComicsByHero(123)

        val stateFlow = viewModel.comics.first()

        assertEquals(stateFlow.first().id, 123)
    }

    @Test
    fun `WHEN getHero and id 123 EXPECT successful response with a hero`() = runTest {

        repository.getHero(123)
        viewModel.getHeroCharacter(123)
        val stateFlow = viewModel.hero.first()

        assertEquals(123,stateFlow?.id)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}