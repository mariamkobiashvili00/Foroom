package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import com.example.foroom.Helper.waitUntilVisible
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

object ChatsPage {

    private const val DEFAULT_TIMEOUT_SEC = 20L

    val navBar: Matcher<View> = withId(R.id.navBar)
    val chatsTab: Matcher<View> = withId(R.id.homeNavigationChats)
    val searchChatInput: Matcher<View> = withId(R.id.searchChatInput)
    val chatsRecyclerView: Matcher<View> = withId(R.id.chatsRecyclerView)

    val searchEditText: Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(searchChatInput))

    fun chatCardTitle(chatName: String): Matcher<View> =
        allOf(
            withId(DesignR.id.chatTitleTextView),
            isDescendantOfA(chatsRecyclerView),
            withText(chatName)
        )

    fun waitUntilDisplayed(matcher: Matcher<View>, timeoutSec: Long = DEFAULT_TIMEOUT_SEC) {
        onView(allOf(matcher, isDisplayed())).waitUntilVisible(timeoutSec)
    }

    fun tap(matcher: Matcher<View>) {
        waitUntilDisplayed(matcher)
        onView(allOf(matcher, isDisplayed())).perform(click())
    }

    fun isCurrentlyDisplayed(matcher: Matcher<View>): Boolean =
        try {
            onView(allOf(matcher, isDisplayed())).check(matches(isDisplayed()))
            true
        } catch (_: NoMatchingViewException) {
            false
        }

    fun typeSearchText(text: String) {
        waitUntilDisplayed(searchEditText)
        onView(searchEditText).perform(replaceText(text), closeSoftKeyboard())
    }
}