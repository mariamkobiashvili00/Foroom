package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.foroom.Helper.waitUntilVisible
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

object ChangeLanguagePage {

    private const val DEFAULT_TIMEOUT_SEC = 20L

    val georgianButton: Matcher<View> = withId(R.id.languageButtonGeo)
    val englishButton: Matcher<View> = withId(R.id.languageButtonEng)

    fun waitUntilDisplayed(matcher: Matcher<View>, timeoutSec: Long = DEFAULT_TIMEOUT_SEC) {
        onView(allOf(matcher, isDisplayed())).waitUntilVisible(timeoutSec)
    }

    fun tap(matcher: Matcher<View>) {
        waitUntilDisplayed(matcher)
        onView(allOf(matcher, isDisplayed())).perform(click())
    }
}