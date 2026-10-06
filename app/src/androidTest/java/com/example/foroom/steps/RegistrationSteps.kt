package com.example.foroom.steps

import com.example.foroom.pages.RegistrationPage

class RegistrationSteps {

    fun verifyRegistrationScreenIsDisplayed(): RegistrationSteps = apply {
        RegistrationPage.waitUntilDisplayed(RegistrationPage.repeatPasswordInput)
        RegistrationPage.waitUntilDisplayed(RegistrationPage.signUpButton)
    }

    fun enterUserName(userName: String): RegistrationSteps = apply {
        RegistrationPage.typeUserName(userName)
    }

    fun enterPassword(password: String): RegistrationSteps = apply {
        RegistrationPage.typePassword(password)
    }

    fun repeatPassword(password: String): RegistrationSteps = apply {
        RegistrationPage.typeRepeatPassword(password)
    }

    fun selectAvatar(index: Int): RegistrationSteps = apply {
        RegistrationPage.waitUntilDisplayed(RegistrationPage.loadedAvatarList)
        RegistrationPage.tapAvatar(index)
        RegistrationPage.waitUntilDisplayed(RegistrationPage.selectedAvatarAt(index))
    }

    fun tapSignUp(): RegistrationSteps = apply {
        RegistrationPage.tapSignUp()
    }

    fun verifyHomeScreenIsDisplayed(): RegistrationSteps = apply {
        RegistrationPage.waitUntilDisplayed(RegistrationPage.homeContainer)
        RegistrationPage.waitUntilDisplayed(RegistrationPage.navBar)
    }
}