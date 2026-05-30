package com.eidgreer.studentpayment.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val encryptedPrefs: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        "student_payment_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    // Password Management
    fun setPassword(password: String) {
        encryptedPrefs.edit().putString(KEY_PASSWORD, password).apply()
    }

    fun getPassword(): String? {
        return encryptedPrefs.getString(KEY_PASSWORD, null)
    }

    fun isPasswordSet(): Boolean {
        return getPassword() != null
    }

    // Theme Management
    fun setNightMode(isNight: Boolean) {
        encryptedPrefs.edit().putBoolean(KEY_NIGHT_MODE, isNight).apply()
    }

    fun isNightMode(): Boolean {
        return encryptedPrefs.getBoolean(KEY_NIGHT_MODE, false)
    }

    // First Launch
    fun setFirstLaunch(isFirst: Boolean) {
        encryptedPrefs.edit().putBoolean(KEY_FIRST_LAUNCH, isFirst).apply()
    }

    fun isFirstLaunch(): Boolean {
        return encryptedPrefs.getBoolean(KEY_FIRST_LAUNCH, true)
    }

    companion object {
        private const val KEY_PASSWORD = "app_password"
        private const val KEY_NIGHT_MODE = "night_mode"
        private const val KEY_FIRST_LAUNCH = "first_launch"
    }
}
