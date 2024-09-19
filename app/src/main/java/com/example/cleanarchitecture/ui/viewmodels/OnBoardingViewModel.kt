package com.example.cleanarchitecture.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.domain.usecase.OnBoardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val onBoardingUseCase: OnBoardingUseCase) : ViewModel() {

    private val _isOnBoardingFinished = mutableStateOf(false)
    val isOnBoardingFinished : State<Boolean> get() = _isOnBoardingFinished

    init {
        viewModelScope.launch {
//            Timber.i("datastore status", "${onBoardingUseCase.isOnBoardingFinished()}")
            _isOnBoardingFinished.value = onBoardingUseCase.isOnBoardingFinished()
            Timber.d("OnboardingViewModel", "Onboarding finished status initialized: ${_isOnBoardingFinished.value}")

        }
    }

    fun completeOnBoarding(){
        viewModelScope.launch{
            onBoardingUseCase.setOnBoardingFinished(true)
            val status = onBoardingUseCase.isOnBoardingFinished()
            Timber.d("status is","$status")
            _isOnBoardingFinished.value = true
            Timber.d("OnboardingViewModel", "Onboarding completed and state updated.")

        }
    }

}