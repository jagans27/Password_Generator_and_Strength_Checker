package com.jagan.passwordutil.Util

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val password = savedStateHandle.getStateFlow("password", "********")
    val strength = savedStateHandle.getStateFlow("strength", "Enter password")

    /*Generating password*/

    fun generatePassword() = CoroutineScope(Dispatchers.IO).launch {
        val capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz"
        val specialCharacters = "!@#$()*-+&^"
        val numbers = "1234567890"
        val combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        val password = CharArray(8)

        password[0] = lowerCaseLetters[(lowerCaseLetters.indices).random()];
        password[1] = capitalCaseLetters[(capitalCaseLetters.indices).random()];
        password[2] = specialCharacters[(specialCharacters.indices).random()];
        password[3] = numbers[(numbers.indices).random()];

        for (i in 4..7)
            password[i] = combinedChars[(combinedChars.indices).random()];

        savedStateHandle["password"] = String(password)
    }


    /*Checking password Strength*/
    fun checkPasswordStrength(password: String) = CoroutineScope(Dispatchers.IO).launch {
        val n = password.length

        if (n == 0) {
            savedStateHandle["strength"] = "Enter password"
            return@launch
        }

        if (n <= 4) {
            savedStateHandle["strength"] = "Password length is too short"
            return@launch
        }

        var hasLower = false
        var hasUpper = false
        var hasDigit = false
        var hasSpecialChar = false

        val specialChar = "!@#$()*-+&^"

        for (ch in password.iterator()) {
            if (ch.isLowerCase()) hasLower = true
            if (ch.isUpperCase()) hasUpper = true
            if (ch.isDigit()) hasDigit = true
            if (ch in specialChar) hasSpecialChar = true
        }

        if ((hasDigit && hasLower && hasUpper && hasSpecialChar) && (n==8)) {
            savedStateHandle["strength"] = "Strong password"
        } else if ((hasLower || hasUpper || hasSpecialChar) && (n>=6)) {
            savedStateHandle["strength"] = "Moderate password"
        } else {
            savedStateHandle["strength"] = "Weak password"
        }
    }
}