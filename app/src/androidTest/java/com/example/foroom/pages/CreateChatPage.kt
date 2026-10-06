package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withParentIndex
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import com.example.design_system.components.image_chooser.ImageChooserItemView
import com.example.foroom.Helper.waitUntilVisible
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

object CreateChatPage {

    private const val DEFAULT_TIMEOUT_SEC = 20L
    private const val SECOND_IMAGE_POSITION_IN_ROW = 2
    private const val FIRST_ROW = 0

    val createChatTab: Matcher<View> = withId(R.id.homeNavigationCreateChat)
    val chatNameInput: Matcher<View> = withId(R.id.chatNameInput)
    val chatImageChooser: Matcher<View> = withId(R.id.chatImageChooser)
    val createChatButton: Matcher<View> = withId(R.id.createChatButton)
    val closeButton: Matcher<View> = withId(R.id.closeButton)
    val chatHeaderView: Matcher<View> = withId(R.id.chatHeaderView)
    val messageInput: Matcher<View> = withId(R.id.messageInput)

    val chatNameEditText: Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(chatNameInput))

    val secondChatImage: Matcher<View> =
        allOf(
            isAssignableFrom(ImageChooserItemView::class.java),
            isDescendantOfA(chatImageChooser),
            withParentIndex(SECOND_IMAGE_POSITION_IN_ROW),
            withParent(withParentIndex(FIRST_ROW))
        )

    fun openedChatName(chatName: String): Matcher<View> =
        allOf(
            withId(DesignR.id.chatNameTextView),
            isDescendantOfA(chatHeaderView),
            withText(chatName)
        )

    fun waitUntilDisplayed(matcher: Matcher<View>, timeoutSec: Long = DEFAULT_TIMEOUT_SEC) {
        onView(allOf(matcher, isDisplayed())).waitUntilVisible(timeoutSec)
    }

    fun tap(matcher: Matcher<View>) {
        waitUntilDisplayed(matcher)
        onView(allOf(matcher, isDisplayed())).perform(click())
    }

    fun typeChatName(chatName: String) {
        waitUntilDisplayed(chatNameEditText)
        onView(chatNameEditText).perform(replaceText(chatName), closeSoftKeyboard())
    }
}