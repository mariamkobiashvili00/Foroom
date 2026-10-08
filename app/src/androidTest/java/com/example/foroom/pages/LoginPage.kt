package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.foroom.Helper.waitUntilVisible
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

object LoginPage {

    private const val DEFAULT_TIMEOUT_SEC = 20L

    val userNameInput: Matcher<View> = withId(R.id.userNameInput)
    val passwordInput: Matcher<View> = withId(R.id.passwordInput)
    val logInButton: Matcher<View> = withId(R.id.logInButton)
    val signUpButton: Matcher<View> = withId(R.id.signUpButton)

    val userNameEditText: Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(userNameInput))
    val passwordEditText: Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(passwordInput))
    val userNameError: Matcher<View> =
        allOf(withId(DesignR.id.descriptionTextView), isDescendantOfA(userNameInput))
    val passwordError: Matcher<View> =
        allOf(withId(DesignR.id.descriptionTextView), isDescendantOfA(passwordInput))

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

    fun tapLogIn() {
        onView(logInButton).perform(click())
    }

    fun tapSignUp() {
        onView(signUpButton).perform(click())
    }
}