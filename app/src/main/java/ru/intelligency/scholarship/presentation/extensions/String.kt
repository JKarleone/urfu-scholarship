package ru.intelligency.scholarship.presentation.extensions

import android.util.Patterns

fun String.matchEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}
