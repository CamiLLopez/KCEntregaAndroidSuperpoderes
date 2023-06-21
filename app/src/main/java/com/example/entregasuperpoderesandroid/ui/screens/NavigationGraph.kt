package com.example.entregasuperpoderesandroid.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.entregasuperpoderesandroid.ui.viewModels.CharacterDetailViewModel
import com.example.entregasuperpoderesandroid.ui.viewModels.CharactersViewModel

@Composable
fun NavigationGraph(charactersViewModel: CharactersViewModel, charactersDetailViewModel: CharacterDetailViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationScreens.SuperHeroScreen.route) {
        composable(NavigationScreens.SuperHeroScreen.route) {
            SuperHeroCharacterListScreen(viewModel = charactersViewModel){
                navController.navigate(NavigationScreens.SuperHeroDetailScreen.createRouteWithArgs(it))
            }
        }

        composable(NavigationScreens.SuperHeroDetailScreen.route, arguments = listOf(
            navArgument(NavigationScreens.SuperHeroDetailScreen.ARG_CHARACTER_ID){
                this.type = NavType.IntType
            }
        )) {
            val characterID = it.arguments?.getInt(NavigationScreens.SuperHeroDetailScreen.ARG_CHARACTER_ID)
            if(characterID != null){
                SuperHeroCharacterDetailScreen(viewModel = charactersDetailViewModel, characterID)
            }else{
                navController.navigateUp()
            }

        }
    }
}