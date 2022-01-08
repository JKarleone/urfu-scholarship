package ru.intelligency.scholarship.data.profile

import android.content.Context
import androidx.core.content.edit
import ru.intelligency.scholarship.domain.profile.models.Profile

class UserSharedPreferences(context: Context) {

    private val sharedPrefs = context.getSharedPreferences(KEY_PREFS, Context.MODE_PRIVATE)

    fun getUserData(): Profile {
        val id = sharedPrefs.getString(KEY_ID, "")!!
        val fullName = sharedPrefs.getString(KEY_FULL_NAME, "")!!
        val academicGroupNumber = sharedPrefs.getString(KEY_ACADEMIC_GROUP_NUMBER, "")!!
        val email = sharedPrefs.getString(KEY_EMAIL, "")!!
        val phoneNumber = sharedPrefs.getString(KEY_PHONE_NUMBER, "")!!

        return Profile(id, fullName, academicGroupNumber, email, phoneNumber)
    }

    fun updateUserData(profile: Profile) {
        sharedPrefs.edit(true) {
            putString(KEY_ID, profile.id)
            putString(KEY_FULL_NAME, profile.fullName)
            putString(KEY_ACADEMIC_GROUP_NUMBER, profile.academicGroupNumber)
            putString(KEY_EMAIL, profile.email)
            putString(KEY_PHONE_NUMBER, profile.phoneNumber)
        }
    }

    fun clearUserData() {
        sharedPrefs.edit(true) {
            putString(KEY_ID, "")
            putString(KEY_FULL_NAME, "")
            putString(KEY_ACADEMIC_GROUP_NUMBER, "")
            putString(KEY_EMAIL, "")
            putString(KEY_PHONE_NUMBER, "")
        }
    }

    companion object {

        private const val KEY_PREFS = "URFU_USER_PREFS"
        private const val KEY_ID = "KEY_ID"
        private const val KEY_FULL_NAME = "KEY_FULL_NAME"
        private const val KEY_ACADEMIC_GROUP_NUMBER = "KEY_ACADEMIC_GROUP_NUMBER"
        private const val KEY_EMAIL = "KEY_EMAIL"
        private const val KEY_PHONE_NUMBER = "KEY_PHONE_NUMBER"
    }
}
