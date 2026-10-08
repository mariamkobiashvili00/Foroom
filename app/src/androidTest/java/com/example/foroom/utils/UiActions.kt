package com.example.foroom.utils

import android.graphics.Rect
import android.os.SystemClock
import android.view.View
import androidx.test.espresso.AmbiguousViewMatcherException
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.constants.Constants.Timeouts
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

fun isDisplayedNow(matcher: Matcher<View>): Boolean =
    try {
        onView(allOf(matcher, isDisplayed())).check(matches(isDisplayed()))
        true
    } catch (_: NoMatchingViewException) {
        false
    } catch (_: AmbiguousViewMatcherException) {
        true
    }

fun becomesTrue(timeoutMs: Long = Timeouts.DEFAULT_MS, condition: () -> Boolean): Boolean {
    val endTime = SystemClock.uptimeMillis() + timeoutMs
    do {
        if (condition()) return true
        SystemClock.sleep(Timeouts.POLL_INTERVAL_MS)
    } while (SystemClock.uptimeMillis() < endTime)
    return false
}

fun becomesDisplayed(matcher: Matcher<View>, timeoutMs: Long = Timeouts.DEFAULT_MS): Boolean =
    becomesTrue(timeoutMs) { isDisplayedNow(matcher) }

fun waitUntilDisplayed(matcher: Matcher<View>, timeoutMs: Long = Timeouts.DEFAULT_MS) {
    if (!becomesDisplayed(matcher, timeoutMs)) {
        throw AssertionError("View was not displayed within $timeoutMs ms: $matcher")
    }
}

fun tap(matcher: Matcher<View>) {
    waitUntilDisplayed(matcher)
    onView(allOf(matcher, isDisplayed())).perform(click())
}

fun enterText(matcher: Matcher<View>, text: String) {
    waitUntilDisplayed(matcher)
    onView(allOf(matcher, isDisplayed())).perform(replaceText(text), closeSoftKeyboard())
}

fun screenBoundsOf(matcher: Matcher<View>): Rect {
    waitUntilDisplayed(matcher)
    val bounds = Rect()
    onView(allOf(matcher, isDisplayed())).check(ViewAssertion { view, noViewFoundException ->
        if (noViewFoundException != null) throw noViewFoundException
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        bounds.set(location[0], location[1], location[0] + view.width, location[1] + view.height)
    })
    return bounds
}

fun clickChildWithId(childId: Int): ViewAction = object : ViewAction {
    override fun getConstraints(): Matcher<View> = isAssignableFrom(View::class.java)

    override fun getDescription(): String = "click child view with id $childId"

    override fun perform(uiController: UiController, view: View) {
        val child = view.findViewById<View>(childId)
            ?: throw AssertionError("Child view with id $childId was not found")
        click().perform(uiController, child)
    }
}