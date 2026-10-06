package com.example.foroom.steps

import com.example.foroom.pages.ChangeLanguagePage
import com.example.foroom.pages.ChangePasswordPage
import com.example.foroom.pages.ProfilePage

class ProfileSteps {

    fun openProfile(): ProfileSteps = apply {
        ProfilePage.tap(ProfilePage.profileTab)
        ProfilePage.waitUntilDisplayed(ProfilePage.changePasswordItem)
    }

    fun changePassword(newPassword: String): ProfileSteps = apply {
        ProfilePage.tap(ProfilePage.changePasswordItem)
        ChangePasswordPage.typePassword(newPassword)
        ChangePasswordPage.typeRepeatPassword(newPassword)
        ChangePasswordPage.tap(ChangePasswordPage.confirmButton)
    }

    fun selectGeorgian(): ProfileSteps = apply {
        ProfilePage.tap(ProfilePage.changeLanguageItem)
        ChangeLanguagePage.tap(ChangeLanguagePage.georgianButton)
    }

    fun selectEnglish(): ProfileSteps = apply {
        ProfilePage.tap(ProfilePage.changeLanguageItem)
        ChangeLanguagePage.tap(ChangeLanguagePage.englishButton)
    }

    fun verifyProfileLabels(changeLanguageText: String, signOutText: String): ProfileSteps = apply {
        ProfilePage.waitUntilDisplayed(ProfilePage.changeLanguageLabel(changeLanguageText))
        ProfilePage.waitUntilDisplayed(ProfilePage.signOutLabel(signOutText))
    }

    fun signOut(): ProfileSteps = apply {
        ProfilePage.tap(ProfilePage.signOutItem)
    }
}