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
        Intents.init()  // Initialize Intents before running tests
    }

    @After
    fun tearDown() {
        Intents.release()  // Release Intents after tests
    }

    @Test
    fun app_launches_and_navigates() {
        // Wait for the initial composition
        composeTestRule.waitForIdle()

        // Verify the start state is loading
        composeTestRule.onNodeWithTag("LoadingScreen").assertIsDisplayed()

        // Wait for the loading to finish and AnimeListInfo to be displayed
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithTag("AnimeListInfo")
                .fetchSemanticsNodes().isNotEmpty()
        }

        // Verify that AnimeListInfo is now displayed
        composeTestRule.onNodeWithTag("AnimeListInfo").assertIsDisplayed()

        // Find and click the first anime item
        composeTestRule.onAllNodesWithTag("AnimeListItem").onFirst().performClick()

        // Verify that AnimeDetailsInfo is now displayed
        composeTestRule.onNodeWithTag("AnimeDetailsInfo").assertIsDisplayed()

        Espresso.pressBack()

        composeTestRule.onNodeWithTag("AnimeListInfo").assertIsDisplayed()
    }

    @Test
    fun click_trailer_button_opens_external_app() {
        // Wait for the AnimeListInfo to be displayed
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithTag("AnimeListItem")
                .fetchSemanticsNodes().isNotEmpty()
        }

        // Find the first AnimeListItem
        val firstListItem = composeTestRule
            .onAllNodesWithTag("AnimeListItem")
            .onFirst()

        // Verify the "Watch Trailer" button exists within the first list item
        firstListItem
            .onChildren()
            .filterToOne(hasText(composeTestRule.activity.getString(R.string.watch_trailer)))
            .assertExists()

        // Click the "Watch Trailer" button within the first list item
        firstListItem
            .onChildren()
            .filterToOne(hasText(composeTestRule.activity.getString(R.string.watch_trailer)))
            .performClick()

        // Verify that the correct intent was sent to open the YouTube app
        intended(
            allOf(
                hasAction(Intent.ACTION_VIEW),
                hasPackage("com.google.android.youtube")
            )
        )
    }
}