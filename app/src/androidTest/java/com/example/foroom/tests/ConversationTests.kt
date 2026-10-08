package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.constants.Constants.Chats
import com.example.foroom.constants.Constants.Messages
import com.example.foroom.constants.Constants.Users
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.steps.ChatListSteps
import com.example.foroom.steps.ConversationSteps
import com.example.foroom.steps.LoginSteps
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConversationTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()
    private val chatListSteps = ChatListSteps()
    private val conversationSteps = ConversationSteps()

    @Before
    fun signInAsUserA() {
        loginSteps
            .startFromLoginScreen()
            .signInOrRegister(Users.USER_A)
        chatListSteps.verifyChatListIsDisplayed()
    }

    @Test
    fun sentMessageInJohnWeekChatIsStillPresentAfterReopeningChat() {
        val message = "${Messages.DRINK_INVITATION} ${uniqueSuffix()}"

        chatListSteps.openOrCreateChat(Chats.JOHN_WEEK)
        conversationSteps
            .verifyConversationIsOpen(Chats.JOHN_WEEK)
            .sendMessage(message)
            .verifyMessageIsDisplayed(message)
            .closeChat()

        chatListSteps.openExistingChat(Chats.JOHN_WEEK)
        conversationSteps
            .verifyConversationIsOpen(Chats.JOHN_WEEK)
            .verifyMessageIsDisplayed(message)
    }

    @Test
    fun sentQuestionIsDisplayedInOwnChat() {
        val question = "${Messages.FAVORITE_MODULE_QUESTION} ${uniqueSuffix()}"

        chatListSteps.openOrCreateChat(Chats.OWN_CHAT)
        conversationSteps
            .verifyConversationIsOpen(Chats.OWN_CHAT)
            .sendMessage(question)
            .verifyMessageIsDisplayed(question)
    }

    @Test
    fun conversationIsContinuedByAnotherAccountInSharedChat() {
        val suffix = uniqueSuffix()
        val greeting = "${Messages.GREETING} $suffix"
        val reply = "${Messages.REPLY} $suffix"
        val fillers = (1..Messages.HISTORY_FILLER_COUNT).map {
            "${Messages.HISTORY_FILLER} $it $suffix"
        }

        chatListSteps.openOrCreateChat(Chats.SHARED_CHAT)
        conversationSteps
            .verifyConversationIsOpen(Chats.SHARED_CHAT)
            .sendMessageAndVerifyIt(greeting)
            .sendMessagesAndVerifyThem(fillers)
            .closeChat()

        loginSteps.switchUser(Users.USER_B)
        chatListSteps.openExistingChat(Chats.SHARED_CHAT)
        conversationSteps
            .verifyConversationIsOpen(Chats.SHARED_CHAT)
            .swipeToOlderMessagesUntilDisplayed(greeting)
            .verifyMessageSender(greeting, Users.USER_A.userName)
            .sendMessage(reply)
            .verifyMessageIsDisplayed(reply)
            .closeChat()

        loginSteps.switchUser(Users.USER_A)
        chatListSteps.openExistingChat(Chats.SHARED_CHAT)
        conversationSteps
            .verifyConversationIsOpen(Chats.SHARED_CHAT)
            .verifyMessageIsDisplayed(reply)
            .verifyMessageSender(reply, Users.USER_B.userName)
    }

    private fun uniqueSuffix(): String = System.currentTimeMillis().toString()
}