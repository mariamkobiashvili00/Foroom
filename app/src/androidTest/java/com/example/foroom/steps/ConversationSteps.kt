package com.example.foroom.steps

import androidx.test.platform.app.InstrumentationRegistry
import com.example.foroom.Helper.swiper
import com.example.foroom.constants.Constants.SwipeConfig
import com.example.foroom.pages.ChatPage
import com.example.foroom.pages.ChatsPage
import com.example.foroom.utils.enterText
import com.example.foroom.utils.isDisplayedNow
import com.example.foroom.utils.screenBoundsOf
import com.example.foroom.utils.tap
import com.example.foroom.utils.waitUntilDisplayed

class ConversationSteps(
    private val page: ChatPage = ChatPage(),
    private val chatsPage: ChatsPage = ChatsPage()
) {

    fun verifyConversationIsOpen(chatTitle: String): ConversationSteps = apply {
        waitUntilDisplayed(page.messageInput)
        waitUntilDisplayed(page.chatTitle(chatTitle))
        waitUntilDisplayed(page.enabledSendMessageButton)
    }

    fun sendMessage(text: String): ConversationSteps = apply {
        enterText(page.messageEditText, text)
        tap(page.enabledSendMessageButton)
    }

    fun verifyMessageIsDisplayed(text: String): ConversationSteps = apply {
        waitUntilDisplayed(page.messageText(text))
    }

    fun verifyMessageSender(text: String, senderName: String): ConversationSteps = apply {
        waitUntilDisplayed(page.messageFromSender(text, senderName))
    }

    fun sendMessageAndVerifyIt(text: String): ConversationSteps = apply {
        sendMessage(text)
        verifyMessageIsDisplayed(text)
    }

    fun sendMessagesAndVerifyThem(texts: List<String>): ConversationSteps = apply {
        texts.forEach { sendMessageAndVerifyIt(it) }
    }

    fun swipeToOlderMessagesUntilDisplayed(text: String): ConversationSteps {
        waitUntilDisplayed(page.messagesRecyclerView)
        val screenHeight = InstrumentationRegistry.getInstrumentation()
            .targetContext.resources.displayMetrics.heightPixels
        val areaTop = screenBoundsOf(page.chatHeaderView).bottom.coerceIn(0, screenHeight)
        val areaBottom = screenBoundsOf(page.messageInput).top.coerceIn(areaTop, screenHeight)
        val areaHeight = areaBottom - areaTop
        val startY = (areaTop + areaHeight * SwipeConfig.START_FRACTION).toInt()
        val endY = (areaTop + areaHeight * SwipeConfig.END_FRACTION).toInt()

        for (attempt in 1..SwipeConfig.MAX_SWIPES) {
            if (isDisplayedNow(page.messageText(text))) return this
            swiper(startY, endY, SwipeConfig.DURATION_MS)
        }
        if (isDisplayedNow(page.messageText(text))) return this
        throw AssertionError(
            "Message was not found after ${SwipeConfig.MAX_SWIPES} swipes: $text"
        )
    }

    fun closeChat(): ConversationSteps = apply {
        tap(page.closeButton)
        waitUntilDisplayed(chatsPage.searchChatInput)
    }
}