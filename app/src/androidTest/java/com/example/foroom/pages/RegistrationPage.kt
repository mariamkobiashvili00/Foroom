package com.example.foroom.pages

import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withChild
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.alternator.foroom.R
import com.example.design_system.components.image_chooser.ImageChooserItemView
import com.example.design_system.components.image_chooser.ImageChooserListView
import com.example.foroom.Helper.waitUntilVisible
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

object RegistrationPage {

    private const val DEFAULT_TIMEOUT_SEC = 20L

    private val onRegistrationScreen: Matcher<View> =
        withParent(withChild(withId(R.id.repeatPasswordInput)))

    val userNameInput: Matcher<View> = allOf(withId(R.id.userNameInput), onRegistrationScreen)
    val passwordInput: Matcher<View> = allOf(withId(R.id.passwordInput), onRegistrationScreen)
    val repeatPasswordInput: Matcher<View> = withId(R.id.repeatPasswordInput)
    val avatarList: Matcher<View> = withId(R.id.listView)
    val signUpButton: Matcher<View> = allOf(withId(R.id.signUpButton), onRegistrationScreen)

    val userNameEditText: Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(userNameInput))
    val passwordEditText: Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(passwordInput))
    val repeatPasswordEditText: Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(repeatPasswordInput))

    val loadedAvatarList: Matcher<View> = allOf(
        avatarList,
        choosingEnabled(),
        hasDescendant(isAssignableFrom(ImageChooserItemView::class.java))
    )

    val homeContainer: Matcher<View> = withId(R.id.homeContainer)
    val navBar: Matcher<View> = withId(R.id.navBar)

    fun avatarAt(index: Int): Matcher<View> =
        object : BoundedMatcher<View, ImageChooserItemView>(ImageChooserItemView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("avatar at index $index")
            }

            override fun matchesSafely(item: ImageChooserItemView): Boolean {
                val list = generateSequence(item.parent) { it.parent }
                    .filterIsInstance<ImageChooserListView>()
                    .firstOrNull() ?: return false
                return avatarsOf(list).indexOf(item) == index
            }
        }

    fun selectedAvatarAt(index: Int): Matcher<View> = allOf(avatarAt(index), selectedAvatar())

    fun waitUntilDisplayed(matcher: Matcher<View>, timeoutSec: Long = DEFAULT_TIMEOUT_SEC) {
        onView(matcher).waitUntilVisible(timeoutSec)
    }

    fun typeUserName(userName: String) {
        onView(userNameEditText)
            .perform(replaceText(userName), closeSoftKeyboard())
    }

    fun typePassword(password: String) {
        onView(passwordEditText)
            .perform(replaceText(password), closeSoftKeyboard())
    }

    fun typeRepeatPassword(password: String) {
        onView(repeatPasswordEditText)
            .perform(replaceText(password), closeSoftKeyboard())
    }

    fun tapAvatar(index: Int) {
        onView(avatarAt(index)).perform(click())
    }

    fun tapSignUp() {
        onView(signUpButton).perform(click())
    }

    private fun avatarsOf(view: View): List<ImageChooserItemView> = when (view) {
        is ImageChooserItemView -> listOf(view)
        is ViewGroup -> view.children.flatMap { avatarsOf(it) }.toList()
        else -> emptyList()
    }

    private fun choosingEnabled(): Matcher<View> =
        object : BoundedMatcher<View, ImageChooserListView>(ImageChooserListView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("avatar list with choosing enabled")
            }

            override fun matchesSafely(item: ImageChooserListView): Boolean = item.isChoosingEnabled
        }

    private fun selectedAvatar(): Matcher<View> =
        object : BoundedMatcher<View, ImageChooserItemView>(ImageChooserItemView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("selected avatar")
            }

            override fun matchesSafely(item: ImageChooserItemView): Boolean = item.isImageSelected
        }
}