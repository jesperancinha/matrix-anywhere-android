package org.jesperancinha.matrixanywhere

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.jesperancinha.matrixanywhere.ui.theme.MatrixAnywhereTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

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
    fun useAppContext(): Unit = runBlocking {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("org.jesperancinha", appContext.packageName)
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag(WIDTH_TAG).performTextInput("3")
        composeTestRule.onNodeWithTag(HEIGHT_TAG).apply {
            performTextClearance()
            performTextInput("3")
        }

        composeTestRule.onNodeWithTag(SUBMIT_MATRIX_TAG).performClick()
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
}