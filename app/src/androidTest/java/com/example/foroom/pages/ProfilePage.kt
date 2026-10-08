package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import org.hamcrest.Matcher

class ProfilePage {
    val changePasswordItem: Matcher<View> = withId(R.id.changePasswordItem)
    val signOutItem: Matcher<View> = withId(R.id.signOutItem)
}