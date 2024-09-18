package com.example.cleanarchitecture.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.domain.usecase.OnBoardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val onBoardingUseCase: OnBoardingUseCase) : ViewModel() {

    private val _isOnBoardingFinished = mutableStateOf(false)
    val isOnBoardingFinished : State<Boolean> get() = _isOnBoardingFinished

    init {
        viewModelScope.launch {
            _isOnBoardingFinished.value = onBoardingUseCase.isOnBoardingFinished()
        }
    }

    fun completeOnBoarding(){
        viewModelScope.launch{
            onBoardingUseCase.setOnBoardingFinished(true)
            _isOnBoardingFinished.value = true
        }
    }
}