package com.example.foroom.steps

import com.example.foroom.pages.HomePage
import com.example.foroom.pages.LoginPage
import com.example.foroom.pages.ProfilePage
import com.example.foroom.utils.tap
import com.example.foroom.utils.waitUntilDisplayed

class ProfileSteps(
    private val page: ProfilePage = ProfilePage(),
    private val homePage: HomePage = HomePage(),
    private val loginPage: LoginPage = LoginPage()
) {

    fun openProfile(): ProfileSteps = apply {
        tap(homePage.profileTab)
        waitUntilDisplayed(page.changePasswordItem)
    }

    fun signOut(): ProfileSteps = apply {
        openProfile()
        tap(page.signOutItem)
        waitUntilDisplayed(loginPage.logInButton)
    }
}