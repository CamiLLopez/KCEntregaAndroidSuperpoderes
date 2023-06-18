package com.example.entregasuperpoderesandroid.ui.screens

import com.example.entregasuperpoderesandroid.ui.screens.NavigationScreens.SuperHeroDetailScreen.ARG_CHARACTER_ID

sealed class NavigationScreens (val route: String) {

    object SuperHeroScreen : NavigationScreens(SUPERHERO_LIST_BASE_ROUTE)
    object SuperHeroDetailScreen : NavigationScreens(SUPERHERO_DETAIL_BASE_ROUTE_TEMPLATE){
        const val ARG_CHARACTER_ID = "heroID"

        fun createRouteWithArgs(heroID: Int): String{
            return SUPERHERO_DETAIL_BASE_ROUTE_TO_FORMAT.format(heroID)
        }
      }
    companion object {
        private const val SUPERHERO_LIST_BASE_ROUTE = "SuperHeroCharacterListScreen"
        private const val SUPERHERO_DETAIL_BASE_ROUTE = "SuperHeroCharacterDetailScreen"
        private const val SUPERHERO_DETAIL_BASE_ROUTE_TO_FORMAT = "$SUPERHERO_DETAIL_BASE_ROUTE/%s"
        private const val SUPERHERO_DETAIL_BASE_ROUTE_TEMPLATE = "$SUPERHERO_DETAIL_BASE_ROUTE/{$ARG_CHARACTER_ID}"
    }
}