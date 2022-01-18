package com.geekbrains.tests.view.search


import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.tests.R
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun seUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun activity_is_resumed_Test() {
        assertEquals(scenario.state, Lifecycle.State.RESUMED)
    }

    @Test
    fun activity_all_views_completely_is_displayed() {
        onView(withId(R.id.searchEditText))
            .check(matches(isCompletelyDisplayed()))
            .check(matches(withHint(R.string.search_hint)))
            .check(matches(isFocused()))
        onView(withId(R.id.toDetailsActivityButton))
            .check(matches(isCompletelyDisplayed()))
            .check(matches(withText(R.string.to_details)))
    }

    @Test
    fun activity_type_some_text_and_press_button() {
        onView(withId(R.id.totalCountTextView))
            .check(matches(withEffectiveVisibility(Visibility.INVISIBLE)))
        onView(withId(R.id.searchEditText))
            .perform(
                click(),
                replaceText("Kotlin"),
                pressImeActionButton()
            )
        onView(withId(R.id.toDetailsActivityButton))
            .perform(click())

        onView(withId(R.id.totalCountTextView))
            .check(matches(isDisplayed()))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
            .check(matches(withText("Number of results: 42")))
    }

    @After
    fun close() {
        scenario.close()
    }
}