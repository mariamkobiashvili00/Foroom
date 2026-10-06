package com.example.foroom.steps

import com.example.foroom.pages.LoginPage

class LoginSteps {

    fun verifyLoginScreenIsDisplayed(): LoginSteps = apply {
        LoginPage.waitUntilDisplayed(LoginPage.logInButton)
        LoginPage.waitUntilDisplayed(LoginPage.userNameInput)
        LoginPage.waitUntilDisplayed(LoginPage.passwordInput)
    }

    fun enterUserName(userName: String): LoginSteps = apply {
        LoginPage.typeUserName(userName)
    }

    fun enterPassword(password: String): LoginSteps = apply {
        LoginPage.typePassword(password)
    }

    fun tapLogIn(): LoginSteps = apply {
        LoginPage.tapLogIn()
    }

    fun logIn(userName: String, password: String): LoginSteps = apply {
        enterUserName(userName)
        enterPassword(password)
        tapLogIn()
    }

    fun openRegistration(): RegistrationSteps {
        LoginPage.tapSignUp()
        return RegistrationSteps()
    }

    fun verifyUserNameErrorIsDisplayed(): LoginSteps = apply {
        LoginPage.waitUntilDisplayed(LoginPage.userNameError)
    }

    fun verifyPasswordErrorIsDisplayed(): LoginSteps = apply {
        LoginPage.waitUntilDisplayed(LoginPage.passwordError)
    }
}