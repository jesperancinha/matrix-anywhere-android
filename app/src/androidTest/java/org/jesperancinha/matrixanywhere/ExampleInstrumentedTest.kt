package org.jesperancinha.matrixanywhere

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun `should run App Context 3 x 3 matrix`(): Unit = runBlocking {
        typeDimensionsByString("3", "3")
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("00").apply {
            performTextClearance()
            performTextInput("4")
        }
        composeTestRule.onNodeWithTag("01").apply {
            performTextClearance()
            performTextInput("5")
        }
        composeTestRule.onNodeWithTag("02").apply {
            performTextClearance()
            performTextInput("6")
        }
        composeTestRule.onNodeWithTag("10").apply {
            performTextClearance()
            performTextInput("3")
        }
        composeTestRule.onNodeWithTag("11").apply {
            performTextClearance()
            performTextInput("8")
        }
        composeTestRule.onNodeWithTag("12").apply {
            performTextClearance()
            performTextInput("9")
        }
        composeTestRule.onNodeWithTag("20").apply {
            performTextClearance()
            performTextInput("1")
        }
        composeTestRule.onNodeWithTag("21").apply {
            performTextClearance()
            performTextInput("2")
        }
        composeTestRule.onNodeWithTag("22").apply {
            performTextClearance()
            performTextInput("3")
        }

        composeTestRule.onNodeWithTag(SUBMIT_CALCULATE_TAG).performClick()
        composeTestRule.onNodeWithTag(CALCULATION_TAG).assertTextContains("The determinant calculation is 12.0")

    }

    @Test
    fun `should not allow text when defining context`(){
        typeDimensionsByString("a","b")
    }

    private fun typeDimensionsByString(width: String, height: String) {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("org.jesperancinha", appContext.packageName)
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag(WIDTH_TAG).performTextInput(width)
        composeTestRule.onNodeWithTag(HEIGHT_TAG).apply {
            performTextClearance()
            performTextInput(height)
        }
        composeTestRule.waitUntil {
            composeTestRule
                .onAllNodesWithTag(SUBMIT_MATRIX_TAG)
                .fetchSemanticsNodes().size == 1
        }
        composeTestRule.onNodeWithTag(SUBMIT_MATRIX_TAG).performClick()

    }
}