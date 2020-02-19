package com.example.deh_kotlin_mpp

actual fun platformName(): String {
    return "Android"
}

actual fun successRegistration(): String {
    return "<Android>Registration successful! You can now login."
}

actual fun failRegistration(): String {
    return "<Android>Error processing your registration. Try again."
}

actual fun emptyFields(): String {
    return "Please make sure that all fields are completed."
}

actual fun invalidLogin(): String {
    return "Invalid credentials. Please try again."
}

actual fun successLogin(): String {
    return "Login successful!"
}