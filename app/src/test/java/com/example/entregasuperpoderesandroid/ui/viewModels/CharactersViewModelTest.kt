package com.example.entregasuperpoderesandroid.ui.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.entregasuperpoderesandroid.data.Repository
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
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

    private val fakeMainThread = newSingleThreadContext("UI thread")
    @Before
    fun setUp() {
        Dispatchers.setMain(fakeMainThread)
        repository = mockk()
        viewModel = CharactersViewModel(repository)
    }

    @Test
    fun getHerosCharacter() {
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}