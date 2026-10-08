package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withParentIndex
import com.alternator.foroom.R
import com.example.design_system.components.image_chooser.ImageChooserItemView
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

class CreateChatPage {
    private val secondImagePositionInRow = 2
    private val firstRow = 0

    val chatNameInput: Matcher<View> = withId(R.id.chatNameInput)
    val chatImageChooser: Matcher<View> = withId(R.id.chatImageChooser)
    val createChatButton: Matcher<View> = withId(R.id.createChatButton)

    val chatNameEditText: Matcher<View> =
        allOf(withId(DesignR.id.inputEditText), isDescendantOfA(chatNameInput))

    val secondChatImage: Matcher<View> =
        allOf(
            isAssignableFrom(ImageChooserItemView::class.java),
            isDescendantOfA(chatImageChooser),
            withParentIndex(secondImagePositionInRow),
            withParent(withParentIndex(firstRow))
        )
}