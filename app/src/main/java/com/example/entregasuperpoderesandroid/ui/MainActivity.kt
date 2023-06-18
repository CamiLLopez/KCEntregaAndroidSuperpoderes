package com.example.entregasuperpoderesandroid.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.entregasuperpoderesandroid.ui.screens.NavigationGraph
import com.example.entregasuperpoderesandroid.ui.theme.EntregaSuperPoderesAndroidTheme
import com.example.entregasuperpoderesandroid.ui.viewModels.CharacterDetailViewModel
import com.example.entregasuperpoderesandroid.ui.viewModels.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val superHeroListViewModel: CharactersViewModel by viewModels()
    private val superHeroDetailViewModel: CharacterDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EntregaSuperPoderesAndroidTheme {
                NavigationGraph(charactersViewModel = superHeroListViewModel, charactersDetailViewModel = superHeroDetailViewModel)
            }
        }
    }
}