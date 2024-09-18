package com.example.cleanarchitecture.domain.usecase

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class OnBoardingUseCase @Inject constructor(private val dataStore: DataStore<Preferences>){
    suspend fun setOnBoardingFinished(isFinished: Boolean){
        dataStore.edit { preferences->
            preferences[stringPreferencesKey("onboarding_finished")] = isFinished.toString()
        }
    }

    suspend fun isOnBoardingFinished(): Boolean{
        val preferences = dataStore.data.first()
        return preferences[stringPreferencesKey("onboarding_finished")]?.toBoolean()?:false
    }

}