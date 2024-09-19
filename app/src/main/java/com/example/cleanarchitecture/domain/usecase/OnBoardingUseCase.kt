package com.example.cleanarchitecture.domain.usecase

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import timber.log.Timber
import javax.inject.Inject

class OnBoardingUseCase @Inject constructor(private val dataStore: DataStore<Preferences>){

    companion object {
        private const val TAG = "OnboardingUseCase"
        private val ONBOARDING_FINISHED_KEY = stringPreferencesKey("onboarding_finished")

    }
    suspend fun setOnBoardingFinished(isFinished: Boolean){
        dataStore.edit { preferences->
            preferences[ONBOARDING_FINISHED_KEY] = isFinished.toString()
            Timber.d(TAG, "Onboarding finished set to: $isFinished")
        }
    }

    suspend fun isOnBoardingFinished(): Boolean{
        val preferences = dataStore.data.first()
        val finished = preferences[ONBOARDING_FINISHED_KEY]?.toBoolean()?:false
        Timber.d(TAG, "Onboarding finished status retrieved: $finished")
        return finished
    }



}