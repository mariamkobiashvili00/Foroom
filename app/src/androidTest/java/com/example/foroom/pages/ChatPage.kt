package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

class ChatPage {
    val chatHeaderView: Matcher<View> = withId(R.id.chatHeaderView)
    val closeButton: Matcher<View> = withId(R.id.closeButton)
    val messagesRecyclerView: Matcher<View> = withId(R.id.messagesRecyclerView)
    val messageInput: Matcher<View> = withId(R.id.messageInput)

    val messageEditText: Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(messageInput))
    val enabledSendMessageButton: Matcher<View> =
        allOf(withId(R.id.sendMessageButton), isDescendantOfA(messageInput), isEnabled())

    fun chatTitle(chatTitle: String): Matcher<View> =
        allOf(
            withId(DesignR.id.chatNameTextView),
            isDescendantOfA(chatHeaderView),
            withText(chatTitle)
        )

    fun messageText(text: String): Matcher<View> =
        allOf(
            withId(DesignR.id.messageTextView),
            isDescendantOfA(messagesRecyclerView),
            withText(text)
        )

    fun messageFromSender(text: String, senderName: String): Matcher<View> =
        allOf(
            withId(R.id.messageView),
            isDescendantOfA(messagesRecyclerView),
            hasDescendant(allOf(withId(DesignR.id.messageTextView), withText(text))),
            hasDescendant(allOf(withId(DesignR.id.userNameTextView), withText(senderName)))
        )
}