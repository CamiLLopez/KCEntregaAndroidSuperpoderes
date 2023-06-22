package com.example.entregasuperpoderesandroid.ui.screens


import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class SuperHeroCharacterFavButtonKtTest{

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `When_fav_true_expected_icon_is_filled`(){

        composeRule.setContent { 
            FavButton(fav = true) {
            }
        }
        composeRule.onNodeWithContentDescription("Favorite Button").assertExists().assertIsDisplayed()
        composeRule.onNodeWithContentDescription("No favorite Button").assertDoesNotExist()
    }

    @Test
    fun `When_fav_false_expected_icon_is_not_filled`(){

        composeRule.setContent {
            FavButton(fav = false) {
            }
        }
        composeRule.onNodeWithContentDescription("No favorite Button").assertExists().assertIsDisplayed()
        composeRule.onNodeWithContentDescription("Favorite Button").assertDoesNotExist()
    }
}