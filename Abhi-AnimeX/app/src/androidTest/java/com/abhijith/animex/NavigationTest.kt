package com.abhijith.animex

import android.content.Intent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasPackage
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun app_launches_and_navigates() {
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("LoadingScreen").assertIsDisplayed()

        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithTag("AnimeListInfo")
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithTag("AnimeListInfo").assertIsDisplayed()

        composeTestRule.onAllNodesWithTag("AnimeListItem").onFirst().performClick()

        composeTestRule.onNodeWithTag("AnimeDetailsInfo").assertIsDisplayed()

        Espresso.pressBack()

        composeTestRule.onNodeWithTag("AnimeListInfo").assertIsDisplayed()
    }

    @Test
    fun click_trailer_button_opens_external_app() {
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithTag("AnimeListItem")
                .fetchSemanticsNodes().isNotEmpty()
        }

        val firstListItem = composeTestRule
            .onAllNodesWithTag("AnimeListItem")
            .onFirst()

        firstListItem
            .onChildren()
            .filterToOne(hasText(composeTestRule.activity.getString(R.string.watch_trailer)))
            .assertExists()

        firstListItem
            .onChildren()
            .filterToOne(hasText(composeTestRule.activity.getString(R.string.watch_trailer)))
            .performClick()

        intended(
            allOf(
                hasAction(Intent.ACTION_VIEW),
                hasPackage("com.google.android.youtube")
            )
        )
    }
}