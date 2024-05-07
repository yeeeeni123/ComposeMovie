package com.yeen.main

sealed class MainEvent {
    data class EnteredEmail(val value: String): MainEvent()
    data class EnteredPassword(val value: String): MainEvent()
    object SignIn: MainEvent()
}
