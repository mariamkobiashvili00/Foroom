package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

class ChatsPage {
    val openChatButtonId: Int = DesignR.id.sendMessageButton

    val searchChatInput: Matcher<View> = withId(R.id.searchChatInput)
    val chatsRecyclerView: Matcher<View> = withId(R.id.chatsRecyclerView)

    val searchEditText: Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(searchChatInput))

    fun chatCardTitle(chatTitle: String): Matcher<View> =
        allOf(
            withId(DesignR.id.chatTitleTextView),
            isDescendantOfA(chatsRecyclerView),
            withText(chatTitle)
        )

    fun chatCardWithTitle(chatTitle: String): Matcher<View> =
        hasDescendant(allOf(withId(DesignR.id.chatTitleTextView), withText(chatTitle)))
}