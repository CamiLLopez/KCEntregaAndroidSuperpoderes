package com.example.entregasuperpoderesandroid.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class SuperHeroCharacterDescriptionModuleKtTest{

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `When_hero_detail_not_exist_expected_element_does_not_exist`(){

        composeRule.setContent {
            DescriptionModule("")
        }
        composeRule.onNodeWithContentDescription("Hero Description in detail").assertDoesNotExist()
    }

    @Test
    fun `When_hero_detail_exist_expected_element_exist`(){

        composeRule.setContent {
            DescriptionModule("Una breve description")
        }
        composeRule.onNodeWithContentDescription("Hero Description in detail").assertExists().assertIsDisplayed()
    }

}