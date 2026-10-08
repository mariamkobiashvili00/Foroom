package com.example.foroom.constants

object Constants {

    data class TestUser(val userName: String, val password: String)

    object Users {
        val USER_A = TestUser("mariam_test", "Mariam123!")
        val USER_B = TestUser("mariam_friend", "Friend123!")
        const val AVATAR_INDEX = 1
    }

    object Chats {
        const val JOHN_WEEK = "johnWeek"
        const val OWN_CHAT = "Mariam Kobiashvili"
        const val SHARED_CHAT = "something"
    }

    object Messages {
        const val DRINK_INVITATION = "let's go for a drink"
        const val FAVORITE_MODULE_QUESTION =
            "Which module do you like most in the Automation Academy?"
        const val GREETING = "Hello from User A"
        const val HISTORY_FILLER = "Filling the history"
        const val REPLY = "Hi, User B is here"
        const val HISTORY_FILLER_COUNT = 25
    }

    object Timeouts {
        const val DEFAULT_MS = 20_000L
        const val SHORT_MS = 5_000L
        const val POLL_INTERVAL_MS = 100L
    }

    object SwipeConfig {
        const val MAX_SWIPES = 20
        const val START_FRACTION = 0.25f
        const val END_FRACTION = 0.8f
        const val DURATION_MS = 600
    }
}