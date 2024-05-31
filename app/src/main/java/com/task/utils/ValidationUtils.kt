package com.task.utils

import android.text.Editable
import java.util.regex.Matcher
import java.util.regex.Pattern

const val EMAIL_PATTERN =
    "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"


fun emailValidator(email: Editable?): Boolean {
    val pattern: Pattern = Pattern.compile(EMAIL_PATTERN)
    val matcher: Matcher = pattern.matcher(email)
    return matcher.matches()
}