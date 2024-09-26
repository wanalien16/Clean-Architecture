package com.example.cleanarchitecture.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.data.dto.DevelopersDTO
import com.example.cleanarchitecture.data.local.DeveloperTable
import com.example.cleanarchitecture.data.remote.ApiService
import com.example.cleanarchitecture.domain.usecase.ColdFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
@HiltViewModel
class ColdFlowViewModel(private val coldFlowUseCase: ColdFlowUseCase) : ViewModel() {
    private val developers = MutableStateFlow<List<DeveloperTable>>(emptyList())
    val devs: StateFlow<List<DeveloperTable>> = developers

    init {
        fetchData()
    }

    private fun fetchData(){
viewModelScope.launch(Dispatchers.IO) {
    runCatching {
        coldFlowUseCase().collect{
            developers.value = it
        }
    }
}
    }



}
