package com.example.foroom.steps

import com.example.foroom.constants.Constants.TestUser
import com.example.foroom.pages.HomePage
import com.example.foroom.pages.LoginPage
import com.example.foroom.utils.becomesTrue
import com.example.foroom.utils.enterText
import com.example.foroom.utils.isDisplayedNow
import com.example.foroom.utils.tap
import com.example.foroom.utils.waitUntilDisplayed

class LoginSteps(
    private val page: LoginPage = LoginPage(),
    private val homePage: HomePage = HomePage(),
    private val registrationSteps: RegistrationSteps = RegistrationSteps(),
    private val profileSteps: ProfileSteps = ProfileSteps()
) {

    fun startFromLoginScreen(): LoginSteps = apply {
        val screenIsReady = becomesTrue {
            isDisplayedNow(homePage.navBar) || isDisplayedNow(page.logInButton)
        }
        if (!screenIsReady) throw AssertionError("Neither home nor login screen was displayed")
        if (isDisplayedNow(homePage.navBar)) profileSteps.signOut()
        verifyLoginScreenIsDisplayed()
    }

    fun verifyLoginScreenIsDisplayed(): LoginSteps = apply {
        waitUntilDisplayed(page.logInButton)
        waitUntilDisplayed(page.userNameInput)
        waitUntilDisplayed(page.passwordInput)
    }

    fun submitCredentials(user: TestUser): LoginSteps = apply {
        enterText(page.userNameEditText, user.userName)
        enterText(page.passwordEditText, user.password)
        tap(page.logInButton)
    }

    fun signInOrRegister(user: TestUser): LoginSteps = apply {
        verifyLoginScreenIsDisplayed()
        submitCredentials(user)
        val loginIsResolved = becomesTrue {
            isDisplayedNow(homePage.navBar) || isDisplayedNow(page.passwordError)
        }
        if (!loginIsResolved) throw AssertionError("Login of ${user.userName} gave no result")
        if (isDisplayedNow(homePage.navBar)) return@apply
        if (!isDisplayedNow(page.userNameError)) {
            throw AssertionError("Account ${user.userName} exists with a different password")
        }
        tap(page.signUpButton)
        registrationSteps.register(user)
        waitUntilDisplayed(homePage.navBar)
    }

    fun switchUser(user: TestUser): LoginSteps = apply {
        profileSteps.signOut()
        signInOrRegister(user)
    }
}