package com.example.deh_kotlin_mpp

expect fun platformName(): String
expect fun successRegistration(): String
expect fun failRegistration(): String
expect fun emptyFields(): String
expect fun invalidLogin(): String
expect fun successLogin(): String

fun createApplicationScreenMessage() : String {
    return "Kotlin Rocks on ${platformName()}"
}

fun isEmptyFields(): String {
    return "<Android> ${emptyFields()}"
}

fun onRegSuccess(): String {
    return "<Android> ${successRegistration()}"
}

fun onRegFail(): String {
    return "<Android> ${failRegistration()}"
}

fun onLoginFail(): String {
    return "<Android> ${invalidLogin()}"
}

fun onLoginSuccess(): String {
    return "<Android> ${successLogin()}"
}