package com.abhijith.animex

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class AnimeListTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun enterAnimeListScreen_showsContent() {
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

        composeTestRule.onNodeWithTag("LoadingScreen").assertDoesNotExist()
        // Verify that AnimeListInfo is now displayed
        composeTestRule.onNodeWithTag("AnimeListInfo").assertIsDisplayed()
    }

    @Test
    fun listScreen_itemContentDisplayed() {
        // Wait for list to load
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithTag("AnimeListInfo")
                .fetchSemanticsNodes().isNotEmpty()
        }

        // Verify list exists
        composeTestRule.onNodeWithTag("AnimeListInfo").assertExists()

        // Verify list contains items
        composeTestRule.onAllNodesWithTag("AnimeListItem").fetchSemanticsNodes().isNotEmpty()

        // Find the first AnimeListItem
        val firstListItem = composeTestRule
            .onAllNodesWithTag("AnimeListItem")
            .onFirst()

        // Verify the "Watch Trailer" button exists within the first list item
        firstListItem
            .onChildren()
            .filterToOne(hasText(composeTestRule.activity.getString(R.string.watch_trailer)))
            .assertExists()
    }

    /* @Test
     fun listScreen_scrollToEnd() {
         // Wait for the AnimeListInfo to be displayed
         composeTestRule.waitUntil(timeoutMillis = 5000) {
             composeTestRule
                 .onAllNodesWithTag("AnimeListInfo")
                 .fetchSemanticsNodes().isNotEmpty()
         }

         // Wait for all 25 items to be loaded
         composeTestRule.waitUntil(timeoutMillis = 50000) {
             composeTestRule.onAllNodesWithTag("AnimeListItem").fetchSemanticsNodes().size == 25
         }

         // Wait for all 25 items to be loaded
         composeTestRule.waitUntil(timeoutMillis = 30000) {
             composeTestRule.onAllNodesWithTag("AnimeListItem").fetchSemanticsNodes().size == 25
         }

         // Verify that we have 25 items
         val totalItemCount =
             composeTestRule.onAllNodesWithTag("AnimeListItem").fetchSemanticsNodes().size
         assertEquals("25", totalItemCount, "Expected 25 items, but found $totalItemCount")

         // Scroll to the last item (index 24, since it's 0-based)
         composeTestRule.onNodeWithTag("AnimeListInfo").performScrollToIndex(24)

         // Wait for the last item to be visible
         composeTestRule.waitUntil(timeoutMillis = 5000) {
             try {
                 composeTestRule.onAllNodesWithTag("AnimeListItem")[24].assertIsDisplayed()
                 true
             } catch (e: AssertionError) {
                 false
             }
         }

         // Verify that the last item is displayed
         composeTestRule.onAllNodesWithTag("AnimeListItem")[24].assertIsDisplayed()
     }*/

    /*@Test
    fun listScreen_errorState() {
        // Assume you have a way to force an error state
        // forceErrorState()

        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onNodeWithTag("ErrorScreen").isDisplayed()
        }

        composeTestRule.onNodeWithText("Something went wrong! Please try again later...").assertIsDisplayed()
    }*/
}