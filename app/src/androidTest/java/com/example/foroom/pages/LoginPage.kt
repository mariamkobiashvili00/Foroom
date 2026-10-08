package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

class LoginPage {
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
}