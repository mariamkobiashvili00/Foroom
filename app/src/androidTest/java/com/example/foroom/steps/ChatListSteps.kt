package com.example.foroom.steps

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.constants.Constants.Timeouts
import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.CreateChatPage
import com.example.foroom.pages.HomePage
import com.example.foroom.utils.becomesDisplayed
import com.example.foroom.utils.clickChildWithId
import com.example.foroom.utils.enterText
import com.example.foroom.utils.tap
import com.example.foroom.utils.waitUntilDisplayed
import org.hamcrest.Matchers.allOf

class ChatListSteps(
    private val page: ChatsPage = ChatsPage(),
    private val homePage: HomePage = HomePage(),
    private val createChatPage: CreateChatPage = CreateChatPage()
) {

    fun verifyChatListIsDisplayed(): ChatListSteps = apply {
        waitUntilDisplayed(homePage.navBar)
        waitUntilDisplayed(page.searchChatInput)
        waitUntilDisplayed(page.chatsRecyclerView)
    }

    fun searchChat(chatTitle: String): ChatListSteps = apply {
        enterText(page.searchEditText, chatTitle)
    }

    fun isChatListed(chatTitle: String, timeoutMs: Long = Timeouts.SHORT_MS): Boolean =
        becomesDisplayed(page.chatCardTitle(chatTitle), timeoutMs)

    fun openListedChat(chatTitle: String): ChatListSteps = apply {
        waitUntilDisplayed(page.chatCardTitle(chatTitle))
        onView(allOf(page.chatsRecyclerView, isDisplayed())).perform(
            RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                page.chatCardWithTitle(chatTitle),
                clickChildWithId(page.openChatButtonId)
            ).atPosition(0)
        )
    }

    fun createChat(chatTitle: String): ChatListSteps = apply {
        tap(homePage.createChatTab)
        enterText(createChatPage.chatNameEditText, chatTitle)
        tap(createChatPage.secondChatImage)
        tap(createChatPage.createChatButton)
    }

    fun openOrCreateChat(chatTitle: String): ChatListSteps = apply {
        verifyChatListIsDisplayed()
        searchChat(chatTitle)
        if (isChatListed(chatTitle)) openListedChat(chatTitle) else createChat(chatTitle)
    }

    fun openExistingChat(chatTitle: String): ChatListSteps = apply {
        verifyChatListIsDisplayed()
        searchChat(chatTitle)
        openListedChat(chatTitle)
    }
}