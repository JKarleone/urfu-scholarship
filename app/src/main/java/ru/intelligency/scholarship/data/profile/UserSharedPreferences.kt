package ru.intelligency.scholarship.data.profile

import android.content.Context
import androidx.core.content.edit
import ru.intelligency.scholarship.domain.profile.models.Profile

class UserSharedPreferences(context: Context) {

    private val sharedPrefs = context.getSharedPreferences(KEY_PREFS, Context.MODE_PRIVATE)

    fun getUserData(): Profile {
        val id = sharedPrefs.getInt(KEY_ID, DEFAULT_ID)
        val fullName = sharedPrefs.getString(KEY_FULL_NAME, DEFAULT_FULL_NAME)!!
        val academicGroupNumber =
            sharedPrefs.getString(KEY_ACADEMIC_GROUP_NUMBER, DEFAULT_ACADEMIC_GROUP_NUMBER)!!
        val email = sharedPrefs.getString(KEY_EMAIL, DEFAULT_EMAIL)!!
        val phoneNumber = sharedPrefs.getString(KEY_PHONE_NUMBER, DEFAULT_PHONE_NUMBER)!!

        return Profile(id, fullName, academicGroupNumber, email, phoneNumber)
    }

    fun updateUserData(profile: Profile) {
        sharedPrefs.edit(true) {
            putInt(KEY_ID, profile.id)
            putString(KEY_FULL_NAME, profile.fullName)
            putString(KEY_ACADEMIC_GROUP_NUMBER, profile.academicGroupNumber)
            putString(KEY_EMAIL, profile.email)
            putString(KEY_PHONE_NUMBER, profile.phoneNumber)
        }
    }

    fun clearUserData() {
        sharedPrefs.edit(true) {
            putInt(KEY_ID, 0)
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

        private const val DEFAULT_ID = 0
        private const val DEFAULT_FULL_NAME = "Глухов Антон Сергеевич"
        private const val DEFAULT_ACADEMIC_GROUP_NUMBER = "РИ-480022"
        private const val DEFAULT_EMAIL = "anton@nezavod.ru"
        private const val DEFAULT_PHONE_NUMBER = "+79527266647"
    }
}
