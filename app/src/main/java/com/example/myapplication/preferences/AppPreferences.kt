package com.example.myapplication.preferences

interface AppPreferences {
    fun isSaveCredentialsSelected(): Boolean
    fun setSaveCredentialsSelected(isSelected: Boolean)
    fun getToken(): String
}