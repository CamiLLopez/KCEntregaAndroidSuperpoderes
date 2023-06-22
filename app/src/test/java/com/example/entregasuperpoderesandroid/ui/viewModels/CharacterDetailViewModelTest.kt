package com.example.entregasuperpoderesandroid.ui.viewModels

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.entregasuperpoderesandroid.data.Repository
import com.example.entregasuperpoderesandroid.utils.generateComicList
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
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

    private val fakeMainThread = newSingleThreadContext("UI thread")


    @Before
    fun setUp() {
        Dispatchers.setMain(fakeMainThread)
        repository = mockk()
        viewModel = CharacterDetailViewModel(repository)
    }

    @Test
    fun `WHEN getComicsByHero EXPECT successful response`() = runTest {

        val valueExpected = generateComicList(16)
        coEvery { repository.getComicsByHero(123) } returns valueExpected
        viewModel.getComicsByHero(123)

        val stateFlow = viewModel.comics.first()

        assert(stateFlow.isEmpty())
    }

    @Test
    fun `WHEN getSeriesByHero EXPECT successful response`() = runTest {
    }

    @Test
    fun `WHEN getHero EXPECT successful response`() = runTest {
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}