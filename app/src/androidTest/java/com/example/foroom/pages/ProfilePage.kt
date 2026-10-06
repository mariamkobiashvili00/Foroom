package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import com.example.foroom.Helper.waitUntilVisible
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

object ProfilePage {

    private const val DEFAULT_TIMEOUT_SEC = 20L

    val profileTab: Matcher<View> = withId(R.id.homeNavigationProfile)
    val changePasswordItem: Matcher<View> = withId(R.id.changePasswordItem)
    val changeLanguageItem: Matcher<View> = withId(R.id.changeLanguageItem)
    val signOutItem: Matcher<View> = withId(R.id.signOutItem)

    fun changeLanguageLabel(text: String): Matcher<View> =
        allOf(
            withId(DesignR.id.listItemTextView),
            isDescendantOfA(changeLanguageItem),
            withText(text)
        )

    fun signOutLabel(text: String): Matcher<View> =
        allOf(
            withId(DesignR.id.listItemTextView),
            isDescendantOfA(signOutItem),
            withText(text)
        )

    fun waitUntilDisplayed(matcher: Matcher<View>, timeoutSec: Long = DEFAULT_TIMEOUT_SEC) {
        onView(allOf(matcher, isDisplayed())).waitUntilVisible(timeoutSec)
    }

    fun tap(matcher: Matcher<View>) {
        waitUntilDisplayed(matcher)
        onView(allOf(matcher, isDisplayed())).perform(click())
    }
}