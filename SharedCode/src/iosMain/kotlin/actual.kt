package com.example.deh_kotlin_mpp

import platform.UIKit.UIDevice

actual fun platformName(): String {
    return UIDevice.currentDevice.systemName() +
            " " +
            UIDevice.currentDevice.systemVersion
}

actual fun successRegistration(): String {
    return "<iOS>Registration successful! You can now login."
}

actual fun failRegistration(): String {
    return "<iOS>Error processing your registration. Try again."
}

actual fun emptyFields(): String {
    return "<iOS>Please make sure that all fields are completed."
}

actual fun invalidLogin(): String {
    return "<iOS>Invalid credentials. Please try again."
}

actual fun successLogin(): String {
    return "<iOS>Login successful!"
}