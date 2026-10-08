package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import org.hamcrest.Matcher

class HomePage {
    val navBar: Matcher<View> = withId(R.id.navBar)
    val chatsTab: Matcher<View> = withId(R.id.homeNavigationChats)
    val createChatTab: Matcher<View> = withId(R.id.homeNavigationCreateChat)
    val profileTab: Matcher<View> = withId(R.id.homeNavigationProfile)
}