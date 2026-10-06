package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.steps.ChatSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.ProfileSteps
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProfileAndChatTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()
    private val profileSteps = ProfileSteps()
    private val chatSteps = ChatSteps()

    @Before
    fun startFromLoginScreen() {
        if (chatSteps.isLoggedIn()) {
            profileSteps.openProfile().signOut()
        }
        loginSteps.verifyLoginScreenIsDisplayed()
    }

    @Test
    fun changePasswordAndVerifyIt() {
        loginSteps.logIn(USER_NAME, PASSWORD)
        chatSteps.verifyHomeScreenIsDisplayed()

        profileSteps.openProfile().changePassword(NEW_PASSWORD)
        loginSteps.verifyLoginScreenIsDisplayed()

        loginSteps.logIn(USER_NAME, NEW_PASSWORD)
        chatSteps.verifyHomeScreenIsDisplayed()

        profileSteps.openProfile().changePassword(PASSWORD)
        loginSteps.verifyLoginScreenIsDisplayed()
    }

    @Test
    fun changeLanguageFromGeorgianToEnglishAndBack() {
        loginSteps.logIn(USER_NAME, PASSWORD)
        chatSteps.verifyHomeScreenIsDisplayed()

        profileSteps.openProfile()
            .selectGeorgian()
            .verifyProfileLabels(CHANGE_LANGUAGE_KA, SIGN_OUT_KA)
            .selectEnglish()
            .verifyProfileLabels(CHANGE_LANGUAGE_EN, SIGN_OUT_EN)
            .selectGeorgian()
            .verifyProfileLabels(CHANGE_LANGUAGE_KA, SIGN_OUT_KA)
    }

    @Test
    fun createChatAndFindItInChatList() {
        val chatName = "$FULL_NAME ${System.currentTimeMillis()}"

        loginSteps.logIn(USER_NAME, PASSWORD)
        chatSteps.verifyHomeScreenIsDisplayed()
            .openCreateChat()
            .createChat(chatName)
            .verifyChatScreenIsOpened(chatName)
            .closeChat()
            .searchChat(chatName)
            .verifyChatIsDisplayedInList(chatName)
    }

    companion object {
        private const val USER_NAME = "mariam_test"
        private const val PASSWORD = "Mariam123!"
        private const val NEW_PASSWORD = "Mariam456!"
        private const val FULL_NAME = "Mariam Kobiashvili"
        private const val CHANGE_LANGUAGE_KA = "ენის შეცვლა"
        private const val SIGN_OUT_KA = "გამოსვლა"
        private const val CHANGE_LANGUAGE_EN = "Change Language"
        private const val SIGN_OUT_EN = "Sign Out"
    }
}