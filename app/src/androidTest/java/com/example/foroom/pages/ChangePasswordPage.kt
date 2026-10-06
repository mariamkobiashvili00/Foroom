package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.foroom.Helper.waitUntilVisible
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

object ChangePasswordPage {

    private const val DEFAULT_TIMEOUT_SEC = 20L

    val passwordInput: Matcher<View> = withId(R.id.passwordInput)
    val repeatPasswordInput: Matcher<View> = withId(R.id.repeatPasswordInput)
    val confirmButton: Matcher<View> = withId(DesignR.id.actionButton)

    val passwordEditText: Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(passwordInput))
    val repeatPasswordEditText: Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(repeatPasswordInput))

    fun waitUntilDisplayed(matcher: Matcher<View>, timeoutSec: Long = DEFAULT_TIMEOUT_SEC) {
        onView(allOf(matcher, isDisplayed())).waitUntilVisible(timeoutSec)
    }

    fun tap(matcher: Matcher<View>) {
        waitUntilDisplayed(matcher)
        onView(allOf(matcher, isDisplayed())).perform(click())
    }

    fun typePassword(password: String) {
        waitUntilDisplayed(passwordEditText)
        onView(passwordEditText).perform(replaceText(password), closeSoftKeyboard())
    }

    fun typeRepeatPassword(password: String) {
        waitUntilDisplayed(repeatPasswordEditText)
        onView(repeatPasswordEditText).perform(replaceText(password), closeSoftKeyboard())
    }
}