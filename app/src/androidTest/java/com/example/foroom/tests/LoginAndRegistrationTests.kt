package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import com.example.foroom.steps.LoginSteps
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext

@RunWith(AndroidJUnit4::class)
class LoginAndRegistrationTests {

    @get:Rule(order = 0)
    val clearSessionRule = object : ExternalResource() {
        override fun before() = clearSession()
        override fun after() = clearSession()
    }

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()

    @Test
    fun validUserNameWithInvalidPasswordShowsPasswordError() {
        loginSteps
            .verifyLoginScreenIsDisplayed()
            .enterUserName(EXISTING_USER_NAME)
            .enterPassword(INVALID_PASSWORD)
            .tapLogIn()
            .verifyPasswordErrorIsDisplayed()
    }

    @Test
    fun invalidUserNameWithInvalidPasswordShowsBothErrors() {
        loginSteps
            .verifyLoginScreenIsDisplayed()
            .enterUserName(uniqueUserName())
            .enterPassword(INVALID_PASSWORD)
            .tapLogIn()
            .verifyUserNameErrorIsDisplayed()
            .verifyPasswordErrorIsDisplayed()
    }

    @Test
    fun registrationWithUniqueUserNameOpensHomeScreen() {
        loginSteps
            .verifyLoginScreenIsDisplayed()
            .openRegistration()
            .verifyRegistrationScreenIsDisplayed()
            .enterUserName(uniqueUserName())
            .enterPassword(VALID_PASSWORD)
            .repeatPassword(VALID_PASSWORD)
            .selectAvatar(AVATAR_INDEX)
            .tapSignUp()
            .verifyHomeScreenIsDisplayed()
    }

    private fun clearSession() = runBlocking {
        GlobalContext.get().get<ForoomUserDataStore>().clearUserData()
    }

    private fun uniqueUserName() = "qa${System.currentTimeMillis()}"

    companion object {
        private const val EXISTING_USER_NAME = "student"
        private const val VALID_PASSWORD = "Test12345!"
        private const val INVALID_PASSWORD = "Wrong12345!"
        private const val AVATAR_INDEX = 1
    }
}