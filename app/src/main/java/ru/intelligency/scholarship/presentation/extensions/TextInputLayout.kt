package ru.intelligency.scholarship.presentation.extensions

import androidx.annotation.StringRes
import com.google.android.material.textfield.TextInputLayout
import ru.intelligency.scholarship.R

fun TextInputLayout.checkField(
    @StringRes errorMessage: Int = R.string.field_error_must_be_not_empty,
    matches: String.() -> Boolean = String::isNotEmpty
): String {
    val text = editText?.text
    val input = text.toString()
    return if (text != null && input.matches()) {
        isErrorEnabled = false
        input
    } else {
        error = context.getString(errorMessage)
        isErrorEnabled = true
        ""
    }
}
