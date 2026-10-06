package com.example.foroom.steps

import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.CreateChatPage
import com.example.foroom.pages.LoginPage
import java.util.concurrent.TimeoutException

class ChatSteps {

    fun isLoggedIn(): Boolean {
        val endTime = System.currentTimeMillis() + SCREEN_TIMEOUT_MS
        while (System.currentTimeMillis() < endTime) {
            if (ChatsPage.isCurrentlyDisplayed(ChatsPage.navBar)) return true
            if (ChatsPage.isCurrentlyDisplayed(LoginPage.logInButton)) return false
            Thread.sleep(POLL_INTERVAL_MS)
        }
        throw TimeoutException("Neither home nor login screen was displayed")
    }

    fun verifyHomeScreenIsDisplayed(): ChatSteps = apply {
        ChatsPage.waitUntilDisplayed(ChatsPage.navBar)
        ChatsPage.waitUntilDisplayed(ChatsPage.searchChatInput)
    }

    fun openCreateChat(): ChatSteps = apply {
        CreateChatPage.tap(CreateChatPage.createChatTab)
        CreateChatPage.waitUntilDisplayed(CreateChatPage.chatNameInput)
    }

    fun createChat(chatName: String): ChatSteps = apply {
        CreateChatPage.typeChatName(chatName)
        CreateChatPage.tap(CreateChatPage.secondChatImage)
        CreateChatPage.tap(CreateChatPage.createChatButton)
    }

    fun verifyChatScreenIsOpened(chatName: String): ChatSteps = apply {
        CreateChatPage.waitUntilDisplayed(CreateChatPage.messageInput)
        CreateChatPage.waitUntilDisplayed(CreateChatPage.openedChatName(chatName))
    }

    fun closeChat(): ChatSteps = apply {
        CreateChatPage.tap(CreateChatPage.closeButton)
        ChatsPage.waitUntilDisplayed(ChatsPage.searchChatInput)
    }

    fun searchChat(chatName: String): ChatSteps = apply {
        ChatsPage.typeSearchText(chatName)
    }

    fun verifyChatIsDisplayedInList(chatName: String): ChatSteps = apply {
        ChatsPage.waitUntilDisplayed(ChatsPage.chatsRecyclerView)
        ChatsPage.waitUntilDisplayed(ChatsPage.chatCardTitle(chatName))
    }

    companion object {
        private const val SCREEN_TIMEOUT_MS = 20_000L
        private const val POLL_INTERVAL_MS = 200L
    }
}