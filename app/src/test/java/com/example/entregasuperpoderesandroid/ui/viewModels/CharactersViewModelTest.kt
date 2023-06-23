package com.example.entregasuperpoderesandroid.ui.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.entregasuperpoderesandroid.data.Repository
import com.example.entregasuperpoderesandroid.utils.generateHerosList
import com.example.entregasuperpoderesandroid.utils.generateHerosListEmpty
import io.mockk.coEvery
import io.mockk.mockk
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

class CharactersViewModelTest {


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CharactersViewModel

    private lateinit var repository: Repository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        repository = mockk()
        viewModel = CharactersViewModel(repository)
    }

    @Test
    fun `WHEN generateHerosList EXPECT successful full list response`() = runTest {

        val valueExpected = generateHerosList()
        coEvery { repository.getHeros() } returns valueExpected
        viewModel.getHerosCharacter()

        val stateFlow = viewModel.state.first()

        assert(stateFlow.isNotEmpty())
    }

    @Test
    fun `WHEN generateHerosList EXPECT failure empty list response`() = runTest {

        val valueExpected = generateHerosListEmpty()
        coEvery { repository.getHeros() } returns valueExpected
        viewModel.getHerosCharacter()

        val stateFlow = viewModel.state.first()

        assert(stateFlow.isEmpty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}