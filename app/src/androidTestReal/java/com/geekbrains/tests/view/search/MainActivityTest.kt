package com.geekbrains.tests.view.search

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class MainActivityTest {

    private val uiDevice: UiDevice = UiDevice.getInstance(getInstrumentation())
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val packageName = context.packageName

    @Before
    fun seUp() {
        uiDevice.pressHome()
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
        uiDevice.wait(hasObject(By.pkg(packageName).depth(0)), TIMEOUT)
    }

    @Test
    fun main_activity_is_started() {
        val textEdit = uiDevice.findObject(By.res(packageName, SEARCH_EDIT_TEXT))
        assertNotNull(textEdit)
    }

    @Test
    fun search_positive() {
        val textEdit = uiDevice.findObject(By.res(packageName, SEARCH_EDIT_TEXT))
        textEdit.text = SEARCHING_TEXT
        val searchButton = uiDevice.findObject(By.text(TEXT_OF_SEARCH_BUTTON))
        searchButton.click()
        val result = uiDevice.wait(findObject(By.textStartsWith(START_TEXT_OF_TOTAL_TEXT)), TIMEOUT)
        assertNotNull(result)
    }

    @Test
    fun check_equals_count_found_positive() {
        val textEdit = uiDevice.findObject(By.res(packageName, SEARCH_EDIT_TEXT))
        textEdit.text = SEARCHING_TEXT
        val searchButton = uiDevice.findObject(By.text(TEXT_OF_SEARCH_BUTTON))
        searchButton.click()
        val totalResult =
            uiDevice.wait(findObject(By.textStartsWith(START_TEXT_OF_TOTAL_TEXT)), TIMEOUT)
        val textCountResults = totalResult.text
        assertNotNull(textCountResults)
        val toDetailsButton = uiDevice.findObject(By.text(TEXT_OF_TO_DETAILS_BUTTON))
        toDetailsButton.clickAndWait(newWindow(), TIMEOUT)
        val totalDetails = uiDevice.findObject(By.res(packageName, TOTAL_TEXT_VIEW))
        assertEquals(textCountResults, totalDetails.text)
    }

    companion object {
        private const val TIMEOUT = 5000L
        private const val SEARCH_EDIT_TEXT = "searchEditText"
        private const val TOTAL_TEXT_VIEW = "totalCountTextView"
        private const val TEXT_OF_SEARCH_BUTTON = "to search"
        private const val TEXT_OF_TO_DETAILS_BUTTON = "to details"
        private const val START_TEXT_OF_TOTAL_TEXT = "Number of results:"
        private const val SEARCHING_TEXT = "kotlin"
    }
}