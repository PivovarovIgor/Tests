package com.geekbrains.tests.view.details

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until.hasObject
import androidx.test.uiautomator.Until.newWindow
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class DetailsActivityTest {

    private val uiDevice: UiDevice = UiDevice.getInstance(getInstrumentation())
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val packageName = context.packageName

    @Before
    fun setUp() {
        uiDevice.pressHome()
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        context.startActivity(intent)
        uiDevice.wait(hasObject(By.pkg(packageName)), TIMEOUT)
    }

    @Test
    fun perform_buttons() {
        val buttonDetails = uiDevice.findObject(By.text(TEXT_OF_TO_DETAILS_BUTTON))
        buttonDetails.clickAndWait(newWindow(), TIMEOUT)
        val decrementButton = uiDevice.findObject(By.res(packageName, DECREMENT_BUTTON_ID))
        val incrementButton = uiDevice.findObject(By.res(packageName, INCREMENT_BUTTON_ID))
        val totalCountTextView = uiDevice.findObject(By.res(packageName, TOTAL_TEXT_VIEW_ID))
        assertNotNull(decrementButton)
        assertNotNull(incrementButton)
        assertNotNull(totalCountTextView)
        assertEquals(totalCountTextView.text, "Number of results: 0")
        incrementButton.click()
        assertEquals(totalCountTextView.text, "Number of results: 1")
        decrementButton.click()
        assertEquals(totalCountTextView.text, "Number of results: 0")
    }

    companion object {
        private const val TIMEOUT = 5000L
        private const val TEXT_OF_TO_DETAILS_BUTTON = "to details"
        private const val DECREMENT_BUTTON_ID = "decrementButton"
        private const val INCREMENT_BUTTON_ID = "incrementButton"
        private const val TOTAL_TEXT_VIEW_ID = "totalCountTextView"
    }
}