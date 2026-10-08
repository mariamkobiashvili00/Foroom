package com.example.foroom.steps

import com.example.foroom.constants.Constants.TestUser
import com.example.foroom.constants.Constants.Users
import com.example.foroom.pages.RegistrationPage
import com.example.foroom.utils.enterText
import com.example.foroom.utils.tap
import com.example.foroom.utils.waitUntilDisplayed

class RegistrationSteps(
    private val page: RegistrationPage = RegistrationPage()
) {

    fun verifyRegistrationScreenIsDisplayed(): RegistrationSteps = apply {
        waitUntilDisplayed(page.repeatPasswordInput)
        waitUntilDisplayed(page.signUpButton)
    }

    fun fillCredentials(user: TestUser): RegistrationSteps = apply {
        enterText(page.userNameEditText, user.userName)
        enterText(page.passwordEditText, user.password)
        enterText(page.repeatPasswordEditText, user.password)
    }

    fun selectAvatar(index: Int = Users.AVATAR_INDEX): RegistrationSteps = apply {
        waitUntilDisplayed(page.loadedAvatarList)
        tap(page.avatarAt(index))
        waitUntilDisplayed(page.selectedAvatarAt(index))
    }

    fun submit(): RegistrationSteps = apply {
        tap(page.signUpButton)
    }

    fun register(user: TestUser): RegistrationSteps = apply {
        verifyRegistrationScreenIsDisplayed()
        fillCredentials(user)
        selectAvatar()
        submit()
    }
}